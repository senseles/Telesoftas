package telesoft;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import lt.telesoft.gildedrose.Item;
import lt.telesoft.repository.TelesoftItemRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TelesoftRepositoryTest
{
	@Autowired
	private TelesoftItemRepository telesoftItemRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void should_find_no_items_if_repository_is_empty()
	{
		final Iterable<Item> items = telesoftItemRepository.findAll();
		assertThat(items).isEmpty();
	}

	@Test
	public void should_delete_all_item()
	{
		entityManager.persist(new Item("TestItem", 1, 2));
		entityManager.persist(new Item("TestItem2", 1, 2));

		telesoftItemRepository.deleteAll();

		assertThat(telesoftItemRepository.findAll()).isEmpty();
	}

	@Test
	public void should_store_a_item() {
		Item Item = telesoftItemRepository.save(new Item("TestItem", 1, 2));

		assertThat(Item).hasFieldOrPropertyWithValue("name", "TestItem");
		assertThat(Item).hasFieldOrPropertyWithValue("sellIn", 1);
		assertThat(Item).hasFieldOrPropertyWithValue("quality", 2);
	}

	@Test
	public void should_find_item_by_id()
	{
		final Item item = new Item("TestItem", 1, 2);
		entityManager.persist(item);

		final Item item2 = new Item("TestItem2", 1, 2);
		entityManager.persist(item2);

		final Item foundItem = telesoftItemRepository.findOne(item2.getId());
		assertThat(foundItem).isEqualTo(item2);
	}
}
