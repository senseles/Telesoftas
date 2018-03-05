package gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import lt.telesoft.gildedrose.GildedRose;
import lt.telesoft.gildedrose.GildedRoseController;
import lt.telesoft.gildedrose.Item;


public class GildedRoseTest
{

	@Test
	public void foo()
	{
		final Item[] items = new Item[] { new Item("foo", 0, 0) };
		final Item item = GildedRoseController.getUpdatedGildedRose(items);
		assertEquals("foo", item.name);
	}

	@Test
	public void update_quality_decrements_items()
	{
		final Item[] items = new Item[] { new Item("foo", 1, 1) };
		final GildedRose app = new GildedRose(items);
		app.updateQuality();
		final Item item = app.getItem("foo");
		assertEquals("foo", item.name);
		assertEquals(0, item.quality);
		assertEquals(0, item.sellIn);
	}

	@Test
	public void once_sell_by_date_has_passed() throws Exception
	{
		final Item[] items = new Item[] { new Item("bar", -1, 2) };
		final GildedRose app = new GildedRose(items);
		app.updateQuality();
		final Item item = app.getItem("bar");
		assertEquals("bar", item.name);
		assertEquals(0, item.quality);
		assertEquals(-2, item.sellIn);
	}

	@Test
	public void quality_Of_an_item_is_never_negative() throws Exception
	{
		final Item[] items = new Item[] { new Item("foo", 2, 2) };
		final GildedRose app = new GildedRose(items);
		app.updateQuality();
		app.updateQuality();
		app.updateQuality();
		final Item item = app.getItem("foo");
		assertEquals("foo", item.name);
		assertEquals(0, item.quality);
		assertEquals(-1, item.sellIn);
	}

	@Test
	public void conjured_items_degrade() throws Exception
	{
		final Item[] items = new Item[] { new Item("Conjured", 2, 30) };
		final GildedRose app = new GildedRose(items);
		app.updateQuality();
		final Item item = app.getItem("Conjured");
		assertEquals("Conjured", item.name);
		assertEquals(28, item.quality);
		assertEquals(1, item.sellIn);
	}

	@Test(expected = RuntimeException.class)
	public void get_item_throwsRuntimeException() throws Exception
	{
		final GildedRose app = new GildedRose(new Item[0]);
		app.getItem("foobar");
	}

}
