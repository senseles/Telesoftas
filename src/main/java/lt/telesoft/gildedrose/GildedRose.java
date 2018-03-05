package lt.telesoft.gildedrose;

public class GildedRose extends LegendaryGildedRose
{
	private final Item[] items;

	public GildedRose(final Item[] items)
	{
		this.items = items;
	}

	public void updateQuality()
	{
		for (final Item item : items)
		{
			degradesNaturallyOrWithAge(item);
			pastSell(item);
			sulfurasSellIn(item);
		}
	}

	private void degradesNaturallyOrWithAge(final Item item)
	{
		if (degradesNaturally(item))
		{
			decrementQuality(item);
		}
		else if (getsBetterWithAge(item))
		{
			incrementQuality(item);

			if (isBackstagePass(item))
			{
				if (tenDaysOrLess(item))
				{
					incrementQuality(item);
				}

				if (fiveDaysOrLess(item))
				{
					incrementQuality(item);
				}
			}
		}
	}

	private void pastSell(final Item item)
	{
		if (pastSellByDate(item))
		{
			if (isAgedBrie(item))
			{
				incrementQuality(item);
			}
			else
			{
				decrementQuality(item);
			}

			if (isBackstagePass(item))
			{
				setQualityToZero(item);
			}
		}
	}

	protected void sulfurasSellIn(final Item item)
	{
		if (!isSulfuras(item))
		{
			item.sellIn = item.sellIn - 1;
		}
	}

	public Item getItem(final String name)
	{
		final Item[] clone = items.clone();
		for (Item item : clone)
		{
			if (name.equalsIgnoreCase(item.name))
			{
				return item;
			}
		}

		throw new RuntimeException("Item not found - " + name);
	}
}