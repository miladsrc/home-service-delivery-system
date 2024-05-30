package util;

import logic.repository.*;
import logic.repository.impl.*;
import logic.service.*;
import logic.service.impl.*;
import org.hibernate.*;

public class ApplicationContext {
    private ApplicationContext() {
    }

    // SESSION FACTORY
    private static final SessionFactory SESSION_FACTORY;
    private static final Session SESSION;

    // REPOSITORY
    private static final OfferRepository OFFER_REPOSITORY;
    private static final OrderRepository ORDER_REPOSITORY;
    private static final ServiceRepository SERVICE_REPOSITORY;
    private static final SubServiceRepository SUBSERVICE_REPOSITORY;
    private static final ExpertRepository EXPERT_REPOSITORY;
    private static final SubServiceExpertRepository SUBSERVICE_EXPERT_REPOSITORY;
    private static final ClientRepository CLIENT_REPOSITORY;
    private static final AdminRepository ADMIN_REPOSITORY;
    private static final UserRepository USER_REPOSITORY;

    // SERVICE
    private static final OfferService OFFER_SERVICE;
    private static final OrderService ORDER_SERVICE;
    private static final ServiceService SERVICE_SERVICE;
    private static final SubServiceService SUBSERVICE_SERVICE;
    private static final ExpertService EXPERT_SERVICE;
    private static final SubServiceExpertService SUBSERVICE_EXPERT_SERVICE;
    private static final ClientService CLIENT_SERVICE;
    private static final AdminService ADMIN_SERVICE;
    private static final UserService USER_SERVICE;

    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();
        SESSION = SESSION_FACTORY.openSession();

        // REPOSITORY
        OFFER_REPOSITORY = new OfferRepositoryImpl(SESSION);
        ORDER_REPOSITORY = new OrderRepositoryImpl(SESSION);
        SERVICE_REPOSITORY = new ServiceRepositoryImpl(SESSION);
        SUBSERVICE_REPOSITORY = new SubServiceRepositoryImpl(SESSION);
        EXPERT_REPOSITORY = new ExpertRepositoryImpl(SESSION);
        SUBSERVICE_EXPERT_REPOSITORY = new SubServiceExpertRepositoryImpl(SESSION);
        CLIENT_REPOSITORY = new ClientRepositoryImpl(SESSION);
        ADMIN_REPOSITORY = new AdminRepositoryImpl(SESSION);
        USER_REPOSITORY = new UserRepositoryImpl(SESSION);

        // SERVICE
        OFFER_SERVICE = new OfferServiceImpl(OFFER_REPOSITORY);
        ORDER_SERVICE = new OrderServiceImpl(ORDER_REPOSITORY);
        SERVICE_SERVICE = new ServiceServiceImpl(SERVICE_REPOSITORY);
        SUBSERVICE_SERVICE = new SubServiceServiceImpl(SUBSERVICE_REPOSITORY);
        EXPERT_SERVICE = new ExpertServiceImpl(EXPERT_REPOSITORY);
        SUBSERVICE_EXPERT_SERVICE = new SubServiceExpertServiceImpl(SUBSERVICE_EXPERT_REPOSITORY);
        CLIENT_SERVICE = new ClientServiceImpl(CLIENT_REPOSITORY);
        ADMIN_SERVICE = new AdminServiceImpl(ADMIN_REPOSITORY);
        USER_SERVICE = new UserServiceImpl(USER_REPOSITORY);
    }

    // GETTERS
    public static OfferService getOfferService() {
        return OFFER_SERVICE;
    }

    public static OrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public static ServiceService getServiceService() {
        return SERVICE_SERVICE;
    }

    public static SubServiceService getSubServiceService() {
        return SUBSERVICE_SERVICE;
    }

    public static ExpertService getExpertService() {
        return EXPERT_SERVICE;
    }

    public static SubServiceExpertService getSubServiceExpertService() {
        return SUBSERVICE_EXPERT_SERVICE;
    }

    public static ClientService getClientService() {
        return CLIENT_SERVICE;
    }

    public static AdminService getAdminService() {
        return ADMIN_SERVICE;
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }
}
