package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;
    private final PartServiceImpl partServiceImpl;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, PartServiceImpl partServiceImpl) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
        this.partServiceImpl = partServiceImpl;
    }

    @Override
    public void run(String... args) throws Exception {


       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */

        List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for (OutsourcedPart part : outsourcedParts) {
            System.out.println(part.getName() + " " + part.getCompanyName());


        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        // Clearing repositories for multiple test runs
        // partRepository.deleteAll();
        // productRepository.deleteAll();
        // outsourcedPartRepository.deleteAll();

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
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products" + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts" + partRepository.count());
        System.out.println(partRepository.findAll());
    }
}


