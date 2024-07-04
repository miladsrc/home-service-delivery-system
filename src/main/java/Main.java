import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import domain.*;
import logic.service.ClientService;
import logic.service.OfferService;
import logic.service.OrderService;
import logic.service.SubServiceExpertService;
import util.ApplicationContext;
import util.SessionFactorySingleton;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, NotFoundException, DatabaseOperationException {

        SubServiceExpertService orderService = ApplicationContext.getSubServiceExpertService();
        orderService.addExpertToSubDuty();
    }
}