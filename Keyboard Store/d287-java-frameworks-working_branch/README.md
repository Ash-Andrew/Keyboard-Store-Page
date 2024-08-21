## WESTERN GOVERNOR UNIVERSITY 
## D287 – JAVA FRAMEWORKS
## Andrew Ashbaker
# Practical Assessment 
C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.

-mainscreen.html Changed the title of the shop to Andrew's Keyboard Shop and the header to Andrew's Keyboard Shop
Added Keyboard Parts: Keycaps, PCB, Stabilizers, Frame60, Frame 65
Added Keyboard Products: Sixtykb, SixtyfiveKB, HHKB Keyboard, sixtyWKLKB, TKLKB

Mainscreen.html - Added color customization
-customized text to blue
-changed button color
-darker blue border
-blue border for table
dark blue background
-shifted the about button more to the top left

Line 8 - added google font

Line 14-53 - added customization of home page

Line 54 - Changed to Andrew' Custom Keyboard Shop

Line 61 - Changed to Andrew's Keyboard Shop

D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

-Line 15 about.html added title 
-about.html line 39-50 added the about me section
-about.html added styling and formatting in lines 10-33
- Line 56-58Added About controller mapping to MainscreenController
-about.html Line 51 Added Main screen button 
-Mainscreen.html Line 59 Added About button to mainscreen.html file


E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.
BootStrapData.java

Insert BootStrapData Lines 79-122
if (partRepository.count() == 0) {
InhousePart keycaps = new InhousePart();
keycaps.setName("keycaps");
keycaps.setPrice(120.00);
keycaps.setInv(10);

            InhousePart switches = new InhousePart();
            switches.setName("switches");
            switches.setPrice(35.00);
            switches.setInv(10);

            InhousePart Stabilzer = new InhousePart();
            Stabilzer.setName("Stabilzer");
            Stabilzer.setPrice(65.00);
            Stabilzer.setInv(10);

            InhousePart Frame60 = new InhousePart();
            Frame60.setName("Frame60");
            Frame60.setPrice(200.00);
            Frame60.setInv(10);

            InhousePart Frame65 = new InhousePart();
            Frame65.setName("Frame65");
            Frame65.setPrice(215.00);
            Frame65.setInv(10);

            partRepository.save(keycaps);
            partRepository.save(switches);
            partRepository.save(Stabilzer);
            partRepository.save(Frame60);
            partRepository.save(Frame65);
        }
        if (productRepository.count() == 0) {
            Product SixtyKB = new Product(11, "SixtyKB", 300.00, 15);
            Product SixtyFiveKB = new Product(12, "SixtyKB", 325.00, 15);
            Product HHKB = new Product(13, "HHKB", 350.00, 15);
            Product SixtyKBWKL = new Product(14, "SixtyKBWKL", 350.00, 15);
            Product TKLKeyboard = new Product(15, "TKLKeyboard", 420.00, 15);

            productRepository.save(SixtyKB);
            productRepository.save(SixtyFiveKB);
            productRepository.save(HHKB);
            productRepository.save(SixtyKBWKL);
            productRepository.save(TKLKeyboard);


F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

line 131 - Added buy button to mainscreen.html
<a th:href="@{/buyProduct(productId=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Buy Now</a>

Created BuyProductController.java - this will check to see if it exists, then the availability of the product, then decrements inventory by one

-Created Purchase.html to display Successful purchase

-Created outOfStock.html file to display if item is out of stock


G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

Insert mainscreen.html
Lines 80-81 And 90-91
< th > Minimum < /th >
< th > Maximum < /th >

        < td th:text="${tempPart.minimum}" > 1 < /td > 
        < td th:text="${tempPart.maximum}" > 1 < /td >
Inserted java.part
@Min (value = 0, message = "Minimum inventory must be > 0")
int minimum;
int maximum;

Part.java Line 97-98 
public void setMinimum(int minimum) { this.minimum = minimum; } 
public int getMinimum() { return this.minimum; }

Line 100-101
public void setMaximum(int maximum) { this.maximum = maximum; } 
public int getMaximum() { return this.maximum 

add  Line 18-19 to inhouse.java and outhouse.java
this.minimum = 0;
this.maximum = 100; 

Renamed Application.properties
From: spring.datasource.url=jdbc:h2:file:~/spring-boot-h2-db102
TO: spring.datasource.url=jdbc:h2:file:~/src/main/resources/spring-boot-h2-db102

Part.Java Lines 89-95
Enforce that the inventory is between the minimum and maximum.
public void validateLimits() {
if (this.inv < this.minimum) {
this.inv = this.minimum;
} else if (this.inv > this.maximum ) {
this.inv = this.maximum;
}
}

Line 52 InhousePartServiceImpl.java and OutsourcedPartServiceImpl.java

        thePart.validateLimits();

Part G Renamed database file name to andrews-keyboard-shop
application.properties Line 6 - Changed Name
Database File - Changed Name


H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
- Did not create a Validator because the buy button doesn't change the inventory 
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.


Part.Java Lines 21-22
@ValidPartInventory
@ValidPartInventoryMinimum

Created PartInventoryMinimumValidator.java

Created ValidPartInventoryMinimum

Created PartInventoryValidator.java

Created ValidPartInventory.java

Line 33 Inserted OutsourcedPartForm.html -- This fixed the missing error code for minimum and maximum inventory on outsourced products  



I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

Part I
PartTest.java Lines 160-175
@Test
void getMinimum() {
int minimum=5;
partIn.setMinimum(minimum);
assertEquals(minimum,partIn.getMinimum());
partOut.setMinimum(minimum);
assertEquals(minimum,partOut.getMinimum());
}

    @Test
    void getMaximum() {
        int maximum=5;
        partIn.setMaximum(maximum);
        assertEquals(maximum,partIn.getMaximum());
        partOut.setMaximum(maximum);
        assertEquals(maximum,partOut.getMaximum());

J.  Remove the class files for any unused validators in order to clean your code.

DeletePartValidator - Removed due to No Usage 

All other validators are being used

K.  Demonstrate professional communication in the content and presentation of your submission.
