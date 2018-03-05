package lt.telesoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.telesoft.gildedrose.Item;


public interface TelesoftItemRepository extends JpaRepository<Item, Long>
{
	List<Item> findByName(String name);

	List<Item> findByNameIgnoreCaseContaining(String name);
}
