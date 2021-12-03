/**
 * Copyright 2021 Markus Bordihn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusbordihn.tokencoins.block.entity;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import de.markusbordihn.tokencoins.item.ModItems;
import de.markusbordihn.tokencoins.item.coinstamp.CoinStampItem;
import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.CoinPressBlock;
import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;
import de.markusbordihn.tokencoins.recipe.CoinPressRecipe;

public class CoinPressBlockEntity extends BaseContainerBlockEntity
    implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);

  private int burnTime = -1;
  private int burnDuration;
  private int cookingProgress;
  private int cookingTotalTime;
  private int stateCheck = 0;
  private CoinPressRecipe recipe = null;
  private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

  protected final ContainerData dataAccess = new ContainerData() {
    public int get(int index) {
      switch (index) {
        case 0:
          return burnTime;
        case 1:
          return burnDuration;
        case 2:
          return cookingProgress;
        case 3:
          return cookingTotalTime;
        default:
          return 0;
      }
    }

    public void set(int index, int value) {
      switch (index) {
        case 0:
          burnTime = value;
          break;
        case 1:
          burnDuration = value;
          break;
        case 2:
          cookingProgress = value;
          break;
        case 3:
          cookingTotalTime = value;
          break;
        default:
      }
    }

    public int getCount() {
      return 5;
    }
  };

  public CoinPressBlockEntity(BlockPos blockPos, BlockState blockState) {
    super(ModBlocks.COIN_PRESS_ENTITY.get(), blockPos, blockState);
  }

  protected Component getDefaultName() {
    return new TranslatableComponent("container.coin_press");
  }

  public boolean isEmpty() {
    for (ItemStack itemStack : this.items) {
      if (!itemStack.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  private Boolean isPowered() {
    return Boolean.valueOf(this.burnTime > 0);
  }

  private Boolean isWorkingDelayed() {
    return Boolean
        .valueOf(this.cookingProgress > 20 && this.cookingProgress < this.cookingTotalTime);
  }

  public ItemStack getItem(int index) {
    return this.items.get(index);
  }

  public static int getMaterialVariant(ItemStack itemStack) {
    if (itemStack.isEmpty()) {
      return 0;
    }
    if (itemStack.is(de.markusbordihn.materialelements.item.ModItems.COPPER_NUGGET.get())) {
      return 1;
    } else if (itemStack.is(Items.GOLD_NUGGET)) {
      return 2;
    } else if (itemStack.is(Items.IRON_NUGGET)) {
      return 3;
    } else if (itemStack.is(de.markusbordihn.materialelements.item.ModItems.STEEL_NUGGET.get())) {
      return 4;
    } else if (itemStack
        .is(de.markusbordihn.materialelements.item.ModItems.NETHERITE_NUGGET.get())) {
      return 5;
    } else if (itemStack.is(Items.COOKIE)) {
      return 6;
    }
    return 0;
  }

  public ItemStack removeItem(int index, int count) {
    return ContainerHelper.removeItem(this.items, index, count);
  }

  public ItemStack removeItemNoUpdate(int index) {
    return ContainerHelper.takeItem(this.items, index);
  }

  public void setItem(int index, ItemStack itemStack) {
    ItemStack itemStackFromIndex = this.items.get(index);
    boolean flag = !itemStack.isEmpty() && itemStack.sameItem(itemStackFromIndex)
        && ItemStack.tagMatches(itemStack, itemStackFromIndex);
    this.items.set(index, itemStack);
    if (itemStack.getCount() > this.getMaxStackSize()) {
      itemStack.setCount(this.getMaxStackSize());
    }
    if (index == CoinPressMenu.MATERIAL_SLOT && !flag) {
      this.cookingTotalTime = getTotalCookTime(this.level, Constants.COIN_PRESS_RECIPE_TYPE, this);
      this.cookingProgress = 0;
      this.setChanged();
    }
  }

  public boolean stillValid(Player player) {
    return (this.level.getBlockEntity(this.worldPosition) == this)
        && player.distanceToSqr(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.5D,
            this.worldPosition.getZ() + 0.5D) <= 64.0D;
  }

  public void clearContent() {
    this.items.clear();
  }

  public void setRecipeUsed(@Nullable Recipe<?> recipe) {
    if (recipe != null) {
      ResourceLocation resourceLocation = recipe.getId();
      this.recipesUsed.addTo(resourceLocation, 1);
    }
  }

  @Nullable
  public Recipe<?> getRecipeUsed() {
    return null;
  }

  public void awardUsedRecipesAndPopExperience(ServerPlayer serverPlayer) {
    List<Recipe<?>> list =
        this.getRecipesToAwardAndPopExperience(serverPlayer.getLevel(), serverPlayer.position());
    serverPlayer.awardRecipes(list);
    this.recipesUsed.clear();
  }

  public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 vec3) {
    List<Recipe<?>> list = Lists.newArrayList();
    for (Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
      level.getRecipeManager().byKey(entry.getKey()).ifPresent(recipeEntry -> {
        list.add(recipeEntry);
        createExperience(level, vec3, entry.getIntValue(),
            ((AbstractCookingRecipe) recipeEntry).getExperience());
      });
    }
    return list;
  }

  public void fillStackedContents(StackedContents stackedContents) {
    for (ItemStack itemStack : this.items) {
      stackedContents.accountStack(itemStack);
    }
  }

  @SuppressWarnings("unchecked")
  private static int getTotalCookTime(Level level,
      RecipeType<? extends AbstractCookingRecipe> recipe, Container container) {
    return level.getRecipeManager()
        .getRecipeFor((RecipeType<AbstractCookingRecipe>) recipe, container, level)
        .map(AbstractCookingRecipe::getCookingTime).orElse(200);
  }

  private static void createExperience(ServerLevel serverLevel, Vec3 vec3, int p_155001_,
      float p_155002_) {
    int i = Mth.floor(p_155001_ * p_155002_);
    float f = Mth.frac(p_155001_ * p_155002_);
    if (f != 0.0F && Math.random() < f) {
      ++i;
    }
    ExperienceOrb.award(serverLevel, vec3, i);
  }

  public static void serverTick(Level level, BlockPos blockPos, BlockState blockState,
      CoinPressBlockEntity blockEntity) {
    ItemStack itemStackFuel = blockEntity.items.get(CoinPressMenu.FUEL_SLOT);
    ItemStack itemStackMaterial = blockEntity.items.get(CoinPressMenu.MATERIAL_SLOT);
    ItemStack itemStackStampBottom = blockEntity.items.get(CoinPressMenu.STAMP_BOTTOM_SLOT);
    ItemStack itemStackStampTop = blockEntity.items.get(CoinPressMenu.STAMP_TOP_SLOT);
    ItemStack itemResult = blockEntity.items.get(CoinPressMenu.RESULT_SLOT);

    // Handle burn time and power status
    if (Boolean.TRUE.equals(blockEntity.isPowered()) && blockEntity.burnTime > 0) {
      blockEntity.burnTime--;
    }

    // Block State check every 20 ticks for powered, working and material status
    if (blockEntity.stateCheck++ == 20) {
      int materialVariant = getMaterialVariant(itemStackMaterial);
      boolean isPowered = blockEntity.isPowered();
      boolean isWorking = blockEntity.isWorkingDelayed();
      if (!Objects.equals(isPowered, blockState.getValue(CoinPressBlock.POWERED))
          || !Objects.equals(isWorking, blockState.getValue(CoinPressBlock.WORKING))
          || materialVariant != blockState.getValue(CoinPressBlock.VARIANT)) {
        BlockState newBlockState = blockState.setValue(CoinPressBlock.POWERED, isPowered)
            .setValue(CoinPressBlock.WORKING, isWorking)
            .setValue(CoinPressBlock.VARIANT, materialVariant);
        level.setBlock(blockPos, newBlockState, 3);
        setChanged(level, blockPos, newBlockState);
      }
      blockEntity.stateCheck = 0;
    }

    // Clear cached recipe if any slot get's empty (or changed).
    if (itemStackMaterial.isEmpty() || itemStackStampBottom.isEmpty()
        || itemStackStampTop.isEmpty()) {
      blockEntity.recipe = null;
    } else if (itemResult.getCount() < itemResult.getMaxStackSize()) {
      // Cache coin press recipe to avoid on-going look ups on each tick.
      if (blockEntity.recipe == null) {
        blockEntity.recipe = CoinPressRecipe.getRecipeFor(blockEntity, level);
      }

      // Check if we could process result to result slot
      boolean canProcessResult = blockEntity.recipe != null
          && (blockEntity.recipe.getResultItem().is(itemResult.getItem()) || itemResult.isEmpty());

      // Power / lit block, if result slot is not overloaded or empty.
      if (Boolean.TRUE.equals(!itemStackFuel.isEmpty() && !blockEntity.isPowered())
          && canProcessResult) {
        power(blockEntity, itemStackFuel);
      }

      // Progress Items, if coin press is active and result slot is not overloaded or empty.
      if (Boolean.TRUE.equals(blockEntity.isPowered()) && canProcessResult) {
        if (blockEntity.cookingProgress == blockEntity.cookingTotalTime) {
          // Create result as soon item is done
          processResult(blockEntity.recipe, blockEntity, itemStackMaterial, itemResult,
              itemStackStampBottom, itemStackStampTop);
          progressAdditionalEffects(blockEntity.recipe, blockPos, level);
        } else {
          // Otherwise update cooking Progress
          ++blockEntity.cookingProgress;
        }
      } else {
        blockEntity.cookingProgress = 0;
      }
    }
  }

  private static void power(CoinPressBlockEntity blockEntity, ItemStack itemStackFuel) {
    // Get burn time for the fuel slot.
    blockEntity.burnTime = net.minecraftforge.common.ForgeHooks.getBurnTime(itemStackFuel,
        Constants.COIN_PRESS_RECIPE_TYPE);

    // Store total burn duration for animation effects.
    blockEntity.burnDuration = blockEntity.burnTime;
    if (itemStackFuel.hasContainerItem()) {
      // Handle Buckets or other fluids
      blockEntity.items.set(CoinPressMenu.FUEL_SLOT, itemStackFuel.getContainerItem());
    } else {
      // Handle normal items
      itemStackFuel.shrink(1);
      if (itemStackFuel.isEmpty()) {
        blockEntity.items.set(CoinPressMenu.FUEL_SLOT, itemStackFuel.getContainerItem());
      }
    }
  }

  private static void processResult(CoinPressRecipe recipe, CoinPressBlockEntity blockEntity,
      ItemStack itemStackMaterial, ItemStack itemResult, ItemStack itemStackStampBottom,
      ItemStack itemStackStampTop) {
    // Process result item
    ItemStack resultItem = recipe.getResultItem();
    blockEntity.cookingProgress = 0;
    if (itemResult.isEmpty()) {
      blockEntity.items.set(CoinPressMenu.RESULT_SLOT, resultItem.copy());
      if (resultItem.getCount() > 1) {
        itemResult.grow(resultItem.getCount() - 1);
      }
    } else if (resultItem.is(itemResult.getItem())) {
      itemResult.grow(resultItem.getCount());
    } else {
      log.error("Unable to process result item {} for {}", resultItem.getItem(), itemResult);
    }

    // Decrease Material Stack
    itemStackMaterial.shrink(1);

    // Increase Item damage for Top stamp and remove, if needed.
    int itemStackStampTopDamage = itemStackStampTop.getDamageValue();
    if (itemStackStampTopDamage + 1 >= itemStackStampTop.getMaxDamage()) {
      itemStackStampTop.shrink(1);
    } else {
      itemStackStampTop.setDamageValue(itemStackStampTopDamage + 1);
    }

    // Increase Item damage for Bottom stamp and remove, if needed.
    int itemStackStampBottomDamage = itemStackStampBottom.getDamageValue();
    if (itemStackStampBottomDamage + 1 >= itemStackStampBottom.getMaxDamage()) {
      itemStackStampBottom.shrink(1);
    } else {
      itemStackStampBottom.setDamageValue(itemStackStampBottomDamage + 1);
    }

    // Set recipe use and grant XP
    blockEntity.setRecipeUsed(recipe);
  }

  // Adding sound effects for specific coins
  public static void progressAdditionalEffects(CoinPressRecipe recipe, BlockPos blockPos,
      Level level) {
    SoundEvent soundEvent = null;
    if (recipe.getResultItem().is(ModItems.NETHERITE_TOKEN_COIN_WITH_WITHER.get())) {
      soundEvent = SoundEvents.WITHER_SPAWN;
    }
    if (soundEvent != null) {
      level.playSound(null, blockPos, soundEvent, SoundSource.BLOCKS, 0.5F, 1.0F);
    }
  }

  @Override
  public void load(CompoundTag compoundTag) {
    super.load(compoundTag);
    this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    ContainerHelper.loadAllItems(compoundTag, this.items);
    this.burnDuration = compoundTag.getInt("burnDuration");
    this.burnTime = compoundTag.getInt("burnTime");
    this.cookingProgress = compoundTag.getInt("cookTime");
    this.cookingTotalTime = compoundTag.getInt("cookTimeTotal");
    CompoundTag recipesUsedCompound = compoundTag.getCompound("recipesUsed");
    for (String keys : recipesUsedCompound.getAllKeys()) {
      this.recipesUsed.put(new ResourceLocation(keys), recipesUsedCompound.getInt(keys));
    }
  }

  @Override
  public void saveAdditional(CompoundTag compoundTag) {
    super.saveAdditional(compoundTag);
    compoundTag.putInt("burnDuration", this.burnDuration);
    compoundTag.putInt("burnTime", this.burnTime);
    compoundTag.putInt("cookTime", this.cookingProgress);
    compoundTag.putInt("cookTimeTotal", this.cookingTotalTime);
    ContainerHelper.saveAllItems(compoundTag, this.items);
    CompoundTag recipesUsedCompound = new CompoundTag();
    this.recipesUsed.forEach((resourceLocation, index) -> recipesUsedCompound
        .putInt(resourceLocation.toString(), index));
    compoundTag.put("recipesUsed", recipesUsedCompound);
  }

  @Override
  public int[] getSlotsForFace(Direction direction) {
    if (direction == Direction.DOWN) {
      return new int[] {CoinPressMenu.RESULT_SLOT};
    }
    return new int[] {CoinPressMenu.FUEL_SLOT, CoinPressMenu.MATERIAL_SLOT,
        CoinPressMenu.STAMP_BOTTOM_SLOT, CoinPressMenu.STAMP_TOP_SLOT};
  }

  @Override
  public boolean canPlaceItemThroughFace(int slotIndex, ItemStack itemStack,
      @Nullable Direction direction) {
    // Allow only specific items per slots.
    if (direction == Direction.DOWN) {
      return false;
    }
    return this.canPlaceItem(slotIndex, itemStack);
  }

  @Override
  public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
    // Only allow the down direction for the result slot.
    return (direction == Direction.DOWN && slotIndex == CoinPressMenu.RESULT_SLOT);
  }

  @Override
  public boolean canPlaceItem(int slotIndex, ItemStack itemStack) {

    // Basic item checks for material, fuel slot and stamp slots
    if ((slotIndex == CoinPressMenu.MATERIAL_SLOT && CoinPressMenu.isNugget(itemStack))
        || (slotIndex == CoinPressMenu.FUEL_SLOT && CoinPressMenu.isFuel(itemStack))
        || (slotIndex == CoinPressMenu.STAMP_TOP_SLOT && CoinPressMenu.isCoinStamp(itemStack)
            && getItem(CoinPressMenu.STAMP_BOTTOM_SLOT).isEmpty())
        || (slotIndex == CoinPressMenu.STAMP_BOTTOM_SLOT && CoinPressMenu.isCoinStamp(itemStack)
            && getItem(CoinPressMenu.STAMP_TOP_SLOT).isEmpty())) {
      return true;
    }

    // More specific check for coin stamps based on their motive
    if (itemStack.getItem() instanceof CoinStampItem item) {

      // More specific check for the top stamp slot
      if (slotIndex == CoinPressMenu.STAMP_TOP_SLOT && getItem(slotIndex).isEmpty()) {
        return item.getStampMotive() == ((CoinStampItem) getItem(CoinPressMenu.STAMP_BOTTOM_SLOT)
            .getItem()).getStampMotive();
      }
      // More specific check for the bottom stamp slot
      if (slotIndex == CoinPressMenu.STAMP_BOTTOM_SLOT && getItem(slotIndex).isEmpty()) {
        return item
            .getStampMotive() == ((CoinStampItem) getItem(CoinPressMenu.STAMP_TOP_SLOT).getItem())
                .getStampMotive();
      }
    }

    return false;
  }

  @Override
  protected AbstractContainerMenu createMenu(int windowId, Inventory inventory) {
    return new CoinPressMenu(windowId, inventory, this, this.dataAccess);
  }

  @Override
  public int getContainerSize() {
    return this.items.size();
  }

  LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
      SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH, Direction.EAST,
          Direction.SOUTH, Direction.WEST);

  @Override
  public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(
      net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
    if (!this.remove && facing != null
        && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      switch (facing) {
        case UP:
          return handlers[0].cast();
        case DOWN:
          return handlers[1].cast();
        case NORTH:
          return handlers[2].cast();
        case EAST:
          return handlers[3].cast();
        case SOUTH:
          return handlers[4].cast();
        case WEST:
          return handlers[5].cast();
        default:
          return handlers[5].cast();
      }
    }
    return super.getCapability(capability, facing);
  }


}
