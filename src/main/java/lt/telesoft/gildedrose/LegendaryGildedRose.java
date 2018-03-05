package lt.telesoft.gildedrose;

public class LegendaryGildedRose
{
	public static final int MAX_QUALITY = 50;
	public static final int MIN_QUALITY = 0;

	protected void incrementQuality(final Item item)
	{
		if (item.quality < MAX_QUALITY)
		{
			item.quality = item.quality + 1;
		}
	}

	protected void decrementQuality(final Item item)
	{
		if (item.quality > MIN_QUALITY)
		{
			if (!isSulfuras(item))
			{
				item.quality = item.quality - 1;
			}

			if (isConjured(item))
			{
				item.quality = item.quality - 1;
			}
		}
	}

	protected void setQualityToZero(final Item item)
	{
		item.quality = 0;
	}

	protected boolean degradesNaturally(final Item item)
	{
		return !getsBetterWithAge(item);
	}

	protected boolean getsBetterWithAge(final Item item)
	{
		return isAgedBrie(item) || isBackstagePass(item);
	}

	protected boolean pastSellByDate(final Item item)
	{
		return item.sellIn < 0;
	}

	protected boolean fiveDaysOrLess(final Item item)
	{
		return item.sellIn < 6;
	}

	protected boolean tenDaysOrLess(final Item item)
	{
		return item.sellIn < 11;
	}

	protected boolean isAgedBrie(final Item item)
	{
		return item.name.equals("Aged Brie");
	}

	protected boolean isBackstagePass(final Item item)
	{
		return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
	}

	protected boolean isSulfuras(final Item item)
	{
		return item.name.equals("Sulfuras, Hand of Ragnaros");
	}

	protected boolean isConjured(final Item item)
	{
		return item.name.equals("Conjured");
	}

}
