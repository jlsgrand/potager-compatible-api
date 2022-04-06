package fr.jgrand.springpotagercompatibleapi.repository;

import fr.jgrand.springpotagercompatibleapi.model.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
    @Query("select friends from Vegetable v join v.friendVegetables friends where v.id = :vegetableId order by friends.id")
    List<Vegetable> findAllFriendVegetables(Long vegetableId);

    @Query("select enemies from Vegetable v join v.enemyVegetables enemies where v.id = :vegetableId order by enemies.id")
    List<Vegetable> findAllEnemyVegetables(Long vegetableId);

    @Modifying
    @Query(value = "delete from vegetable_friends where vegetable_friends.id = :id or vegetable_friends.friend_id = :id", nativeQuery = true)
    void deleteFriendsById(Long id);

    @Modifying
    @Query(value = "delete from vegetable_enemies where vegetable_enemies.id = :id or vegetable_enemies.enemy_id = :id", nativeQuery = true)
    void deleteEnemiesById(Long id);
}
