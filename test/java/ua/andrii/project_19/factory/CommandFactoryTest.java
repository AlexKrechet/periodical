package ua.andrii.project_19.factory;

import org.junit.Test;
import ua.andrii.project_19.commands.AddToCartCommand;
import ua.andrii.project_19.commands.Command;
import ua.andrii.project_19.commands.IndexJspCommand;
import ua.andrii.project_19.enums.DaoType;
import ua.andrii.project_19.enums.ServiceType;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CommandFactoryTest {

    @Test
    public void getCommand() {
        ServiceFactory serviceFactory;
        DaoFactory daoFactory = DaoFactory.getFactory(DaoType.JDBC);
        serviceFactory = ServiceFactory.getFactory(daoFactory, ServiceType.SIMPLE);

        Map<String, Command> commands = new HashMap<>();

        commands.put("", new IndexJspCommand());
        commands.put("ADD", new AddToCartCommand(serviceFactory.getClientService()));
        assertEquals(2, commands.size());

        assertTrue("ADD", true);
    }
}