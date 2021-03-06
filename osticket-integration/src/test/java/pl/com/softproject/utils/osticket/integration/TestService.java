package pl.com.softproject.utils.osticket.integration;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import pl.com.softproject.utils.osticket.integration.config.OsTicketConfig;
import pl.com.softproject.utils.osticket.integration.encoder.Base64FileEncoder;
import pl.com.softproject.utils.osticket.integration.model.Ticket;
import pl.com.softproject.utils.osticket.integration.service.OsTicketIntegrationServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class TestJackson
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
@Ignore
public class TestService {

    private OsTicketIntegrationServiceImpl osTicketIntegrationService = new OsTicketIntegrationServiceImpl();
    private List<File> files = new ArrayList<>();

    public TestService() {
        // url without api/tickets.json
        String url = null;
        // api key
        String key = null;

        // file path to attach
        File pathToFile = null;

        Assert.assertNotNull(pathToFile);
        Assert.assertNotNull(url);
        Assert.assertNotNull(key);

        files.add(pathToFile);

        osTicketIntegrationService.setConfig(new OsTicketConfig(url, key));
        osTicketIntegrationService.setFileEncoder(new Base64FileEncoder());
    }

    @Test
    public void testService() throws Exception {

        Ticket ticket = new Ticket()
                .setAlert(false)
                .setAutoRespond(false)
                .setEmail("email@example.com")
                .setMessage("Message")
                .setName("FirstName SecondName")
                .setTopicId(11)
                .setSubject("Subject");

        osTicketIntegrationService.appendFilesToTicket(ticket, files);

        String number = osTicketIntegrationService.createTicket(ticket);

        Assert.assertNotNull(number);
    }
}
