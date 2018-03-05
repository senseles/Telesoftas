package lt.telesoft.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GildedRoseHelper
{
	private final static Logger LOG = LoggerFactory.getLogger(GildedRoseHelper.class);

	public static Item getUpdatedGildedRose(final Item[] items)
	{
		LOG.info("Gilded Rose update: " + items);
		GildedRose gildedRose = new GildedRose(items);
		gildedRose.updateQuality();
		return gildedRose.items[0];
	}
}
