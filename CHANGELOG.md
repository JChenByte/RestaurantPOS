[0.1.1] - (06/03/2016)
------------------
### Added
 * Serializable.
 * Save orders to txt files.
 * Order files processing.
  * Multi-threaded
  * Get aggregated sub-total, total, and tip of orders with id ranging from a to b.
  * Get sales report of orders with id ranging from a to b.
  
### Changed
 * Add and remove entree from order is now rely on String rather than Entree object.



[0.1.0] - (06/03/2016)
------------------
### Initial Release Features (Model):
 * Manage a single restaurant.
 * Ordering.
  * Add and modify entree
  * Order Types: Dine-in, Carry-out, Delivery
  * Order Status: Open, Paid, Delivered
  * Tip
  * Tax
  * Create receipt
  * Change order type
 * Entree.
  * Add or remove entree from menu
  * Modify ingredients of an entree
  * Categorize entree
  * Adjust pricing
  * Create menu
 * Inventory Management.
  * Add new ingredients
  * Replenish and reduce inventory
 * Employee Management.
  * Add new employee
  * Employee role: Regular, Manager
  * Employee password
 
