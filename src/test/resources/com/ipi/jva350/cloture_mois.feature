Feature: Clôture de mois pour un salarié aide à domicile

  Scenario: Clôturer le mois de février pour un salarié
    Given un salarié aide à domicile nommé "Dupont"
    When je clôture le mois de "février"
    Then le mois de "février" est clôturé pour "Dupont"
    And le nombre de jours de congé restants est mis à jour