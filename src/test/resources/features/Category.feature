@Category
  Feature: verify 4 categories displays on "Ô tô, xe máy, xe đạp"
    This feature ensures 4 sub categories are displayed
  Background:
    Given User open Tiki application
    And User click to Danh mục
    Scenario: Verify there are 4 sub categories displayed by default
      When User scroll to "Hàng Quốc Tế" in left menu
      And User scroll to panel "Ô tô, xe máy, xe đạp"
      Then User should see 4 sub categories in panel "Ô tô, xe máy, xe đạp"