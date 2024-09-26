@ProductDetail
  Feature: Verify product details displays correctly
    This feature ensures product details displays correctly
  Background:
    Given User open Tiki application
    And User click to Danh mục
    Scenario: Verify product details are displayed
      When User scroll to "Hàng Quốc Tế" in left menu
      And User scroll to panel "Thời Trang"
      And User click to the "Thời trang nam"
      And User click to first product
      And User scroll to the "Thông tin chi tiết"
      Then User should see "Danh mục"
      And User should see "Thương hiệu"
      And User should see "Xuất xứ thương hiệu"