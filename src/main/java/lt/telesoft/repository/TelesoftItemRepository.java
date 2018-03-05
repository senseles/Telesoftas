package lt.telesoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.telesoft.gildedrose.Item;


public interface TelesoftItemRepository extends JpaRepository<Item, Long>
{

}
