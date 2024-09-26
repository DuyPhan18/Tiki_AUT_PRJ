@ProductItem
  Feature: Verify items would be displayed on panel "Sách Tiếng Việt"
    This feature ensures that 24 items would be displayed
  Background:
    Given User open Tiki application
    And User click to Danh mục
    Scenario: Verify 24 items is displayed
      When User scroll to "Nhà Sách Tiki" in left menu
      And User scroll to panel "Sách tiếng Việt"
      Then User should see 24 items in panel "Sách tiếng Việt"