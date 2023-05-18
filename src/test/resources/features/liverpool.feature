Feature: Liverpool Test

  Background:
    Given the user navigates to "https://www.liverpool.com.mx/tienda/home"

  Scenario: Search playstation and validate its title and price
    When searches for "playstation" using the search bar
    And verifies there are games for playstation5 and other playstation consoles
    Then selects a "Consola PlayStation 5 edición digital 825 GB"
    And validates that title should be "Consola PlayStation 5 edición digital 825 GB" and price "$10,699"

  @prueba
  Scenario: check size and price filters are working
    When searches for "smart tv" using the search bar
    And verifies that size and price filters are displayed
    Then filters the results by size
    And filters the results by price
    And filters the results by brand "sony"
    And validates the results count

  Scenario: validate menu and filter by brand
    When selects categorias menu
    And moves to perfumes hombre inside belleza submenu
    Then filters the results by brand "dior"
