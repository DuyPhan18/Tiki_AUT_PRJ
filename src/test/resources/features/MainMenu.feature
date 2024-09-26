@MainMenu
Feature: Verify icons would be displayed
  This feature ensures that icons at menu bar would be displayed
  Background:
    Given User open Tiki application
  Scenario Outline:
    Then User shoud see icons of "<menuItemName>" at menu bar

    Examples:
      | menuItemName |
      | Trang chủ    |
      | Danh mục     |
      | Trợ lý       |
      | Tin mới      |
      | Tài khoản    |