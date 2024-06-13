import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import domain.Admin;
import domain.Offer;
import domain.Order;
import domain.User;
import logic.service.OfferService;
import util.ApplicationContext;
import util.SessionFactorySingleton;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, NotFoundException, DatabaseOperationException {

        OfferService offerService = ApplicationContext.getOfferService();

        Order order = Order.builder()
                .price(12.0)
                .description("Customer offers order")
                .time(LocalDateTime.now())
                .build();
        // Set properties for the offer and assign it to the 'offer' field
        Offer offer = Offer.builder()
                .id(1L)
                .description("offer description")
                .price(5000.0)
                .time(LocalDateTime.now())
                .order(order)
                .build();

        offerService.saveOrUpdateOffer(offer);
    }
}