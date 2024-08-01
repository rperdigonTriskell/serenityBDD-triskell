@Dashboard
Feature: Dashboard parts

  Background:
    Given go to web Triskell
    Then check to "Login" has loaded
    When send credential "username" to element "Username"
    And send credential "password" to element "Password"
    And click in "Validate"
    Then check to "Dashboard" has loaded

  @PROD #@AWS @DEV @NR @PROD
  Scenario: See All components Dashboard
    Then check to the following sidebar elements are:
      | element             | visibility |
      | Home Page           | visible    |
      | Solutions           | visible    |
      | Portfolio           | visible    |
      | Project             | visible    |
      | Agile Task          | visible    |
      | Request             | visible    |
      | Product             | visible    |
      | Sprint              | visible    |
      | Issue               | visible    |
      | Department          | visible    |
      | Timesheet           | visible    |
      | Reports             | visible    |
      | Strategic Plan      | visible    |
      | Strategic Objective | visible    |
      | Risk                | visible    |
      | Task                | visible    |
      | Testing Comp        | visible    |
      | Favorites           | visible    |
      | IT (Team 1)         | visible    |
    Then check to the following heading elements are:
      | element               | visibility |
      | Sidebar icon          | visible    |
      | Search                | visible    |
      | Create                | visible    |
      | Configuration icon    | visible    |
      | Supersede icon        | visible    |
      | Notifications icon    | visible    |
      | User icon             | visible    |
      | Home Page             | visible    |
      | Activity Feed         | visible    |
      | Resource Costs Budget | visible    |
      | Dashboard 1           | visible    |
      | +                     | visible    |
    Then check to the following dashboard elements are:
      | element                     | visibility |
      | My Items                    | visible    |
      | Favorites                   | visible    |
      | Last Modified               | visible    |
      | Subscriptions               | visible    |
      | Favorites board             | visible    |
      | My Alerts and Notifications | visible    |
      | My Calendar View            | visible    |



#  Identificar las funcionalidades clave de la sección web:
#
#  Observa la imagen y determina las funcionalidades principales que se deben probar. En este caso, podría ser la gestión de elementos en "My Items", la verificación de alertas y notificaciones en "My Alerts and Notifications", y la visualización de eventos en "My Calendar View".
#  Escribir los escenarios en Gherkin:
#
#  Gherkin es un lenguaje específico de dominio (DSL) que usa una sintaxis simple de lenguaje natural para definir los casos de prueba. Un escenario típico en Gherkin incluye los pasos: Given, When, Then, And.
#  Ejemplos de escenarios en Gherkin:
#  Escenario 1: Visualización de elementos en "My Items"
#  gherkin
#  Copy code
#Feature: Visualización de elementos en "My Items"
#
#  Scenario: El usuario ve los elementos en "My Items"
#    Given el usuario está en la página de inicio de Triskell
#    When el usuario navega a la sección "My Items"
#    Then el usuario debe ver una lista de elementos
#    And el primer elemento debe ser "IT (Team 1)"
#  Escenario 2: Gestión de alertas y notificaciones
#  gherkin
#  Copy code
#Feature: Gestión de alertas y notificaciones
#
#  Scenario: El usuario no tiene alertas y notificaciones
#    Given el usuario está en la página de inicio de Triskell
#    When el usuario navega a la sección "My Alerts and Notifications"
#    Then el usuario debe ver un mensaje indicando que no hay alertas o notificaciones
#  Escenario 3: Visualización del calendario
#  gherkin
#  Copy code
#Feature: Visualización del calendario
#
#  Scenario: El usuario ve el calendario en "My Calendar View"
#    Given el usuario está en la página de inicio de Triskell
#    When el usuario navega a la sección "My Calendar View"
#    Then el usuario debe ver el calendario del mes actual
#    And la fecha actual debe estar resaltada
#  Integrar las pruebas Gherkin con el framework en Java:
#
#  Usa una herramienta como Cucumber para ejecutar las pruebas escritas en Gherkin. Cucumber se integra bien con Java y te permite definir los pasos en Gherkin y mapearlos a métodos en Java.
