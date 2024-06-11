package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import domain.Offer;
import domain.Order;
import logic.service.OfferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

class OfferServiceImplTest {

    private Offer offer;
    private Order order;


    @Test
    public void testSaveOrUpdate() throws DatabaseOperationException {

        //Arrange
        order = Order.builder()
                .price(12.0)
                .description("Customer offers order")
                .time(LocalDateTime.now())
                .build();
        offer = Offer.builder()
                .id(2L)
                .description("offer description")
                .price(5000.0)
                .time(LocalDateTime.now())
                .order(order)
                .build();
        OfferService offerService = ApplicationContext.getOfferService();


        // Act
        Offer savedOffer = offerService.saveOrUpdateOffer(offer);


        // Assert
        Assertions.assertNotNull(savedOffer, "The offer should be saved and returned");
        Assertions.assertEquals("offer description", savedOffer.getDescription(), "The description should match");
        Assertions.assertEquals(5000.0, savedOffer.getPrice(), 0.0, "The price should match");
    }


    @Test
    public void testFindById() throws NotFoundException, DatabaseOperationException {

        //Arrange
        OfferService offerService = ApplicationContext.getOfferService();

        // Act
        Offer foundOffer = offerService.findById(1L);

        // Assert
        Assertions.assertNotNull(foundOffer, "The offer should be found");
    }


    @Test
    public void testDelete() throws DatabaseOperationException {

        //Arrange
        offer = Offer.builder()
                .id(2L)
                .description("offer description")
                .price(5000.0)
                .time(LocalDateTime.now())
                .order(order)
                .build();
        OfferService offerService = ApplicationContext.getOfferService();

        // Act
        offerService.deleteById(offer.getId());

        // Assert
        try {
            offerService.findById(2L);
            Assertions.fail("The offer should not be found after deletion");
        } catch (NotFoundException expected) {
            System.out.println(expected.getMessage());
        }
    }

    @Test
    public void testFindAll()  {

        OfferService offerService = ApplicationContext.getOfferService();

        // Act
        List<Offer> allOffers = offerService.findAll();

        // Assert
        Assertions.assertNotNull(allOffers, "The list of offers should not be null");
        Assertions.assertFalse(allOffers.isEmpty(), "The list of offers should not be empty");
    }
}


