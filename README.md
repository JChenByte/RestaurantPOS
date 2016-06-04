# Restaurant POS
* MVC Architecture (Currently Implemented Model ONLY)

Main Features
------
* Manage a single restaurant.
* Serializable.
* Ordering.
 * Add and modify entree
 * Order Types: Dine-in, Carry-out, Delivery
 * Order Status: Open, Paid, Delivered
 * Tip
 * Tax
 * Create receipt
 * Change order type
 * Ability to save orders as txt files.
* Order files processing.
  * Multi-threaded
  * Get aggregated sub-total, total, and tip of orders with id ranging from a to b.
  * Get sales report of orders with id ranging from a to b.
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


Author
------
Jie Chen
 - [GitHub](https://github.com/JChenByte) 


Release History
---------------
See [CHANGELOG.md](https://github.com/JChenByte/RestaurantPOS/blob/master/CHANGELOG.md)


License
-------
Copyright © 2016 [Jie Chen](https://github.com/JChenByte). Licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
