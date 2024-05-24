package com.group.azura.maraissa.controleQualite.repository.postgres;

import com.group.azura.maraissa.controleQualite.entities.postgres.Action;
import com.group.azura.maraissa.controleQualite.entities.postgres.Menu;
import com.group.azura.maraissa.controleQualite.entities.postgres.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action,Long> {
    @Query("select p.actions from Role r join r.permissions p where r =:role and p.menu=:menu ")
    List<Action> findActionsOfMenuAndrole(Menu menu, Role role);
}
