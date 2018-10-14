package grailsproject


import fr.mbds.grails.fr.mbds.grails.springsec.Role;
import fr.mbds.grails.fr.mbds.grails.springsec.User;
import fr.mbds.grails.fr.mbds.grails.springsec.UserRole;


/* partie nfc
import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
*/

class BootStrap {
    /*
    private static Card card = null;
    private static CardChannel channel = null;
    public static byte[] MY_SERVICE_AID = [ (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x12, (byte) 0x34, (byte) 0x56];
    public static byte[] SELECT_APDU_HEADER = [0x00, (byte) 0xA4, 0x00, 0x04];
    //public static byte[] SELECT_APDU = {SELECT_APDU_HEADER, 0x08, MY_SERVICE_AID, 0x00};
    public static byte[] myCmd = [ (byte) 0xFF, (byte) 0xCA, 0x00, 0x00, 0x00];*/

    def init = { servletContext ->

        def adminRole = Role.findOrSaveWhere('authority': 'ROLE_ADMIN')
        def userRole = Role.findOrSaveWhere('authority': 'ROLE_USER')
        def admin = User.findOrSaveWhere(username: 'admin', password: 'admin', enabled: true)
        def user = User.findOrSaveWhere(username: 'user', password: 'user', enabled: true)

        //String path = 'uploads/images/users/'

        //def img = new Image(name: 'Thiaw', description: 'profile', url: path + 'thiaw.jpg', user: admin).save(flush: true, failOnError: true)
        //def msg = new Message(author: admin, target: user, content: "salut")

        //admin.addToMessages(msg)


        if (!admin.authorities.contains(adminRole)) {
            UserRole.create(admin, adminRole, true)
        }

        if (!user.authorities.contains(userRole)) {
            UserRole.create(user, userRole, true)
        }

        //executeCommand(myCmd); --> nfc

        User.withSession { it.flush() }
        //def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true, failOnError: false)
        //def gamingRole = new Role(authority: 'ROLE_USER').save(flush:true, failOnError: false)

        //def adminUser = new User(username: 'admin', password: 'pw0').save(flush:true, failOnError: false)

        //String path = "C:\\Users\\thiaw\\Documents\\mbds\\grails\\grailsProject\\grails-app\\uploads\\images\\users\\"
        //String path1 = 'uploads/images/users/'
        //def m = new Image(name: 'La Roustide', description: 'restaurant', url: path).save(flush: true, failOnError: true)

        //UserRole.create(adminUser, adminRole, true)
    }

       /*   partie nfc

        private static void executeCommand(byte[] apdu) {

            try {
                if (openConnection()) {
                    byte []rep = null;
                    ATR atr = card.getATR() ;
                    rep = atr.getBytes() ;
                    try {
                        System.out.println("ATR: "+ byteArrayToHexString(rep));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (channel!=null) {
                        try {
                            System.out.println("Command: "+ byteArrayToHexString(apdu));
                            apdu = sendCommand(apdu, channel);
                            if (apdu!=null)
                                System.out.println("Response: "+ byteArrayToHexString(apdu));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static boolean openConnection() {
            TerminalFactory factory = TerminalFactory.getDefault();
            CardTerminals cardterminals = factory.terminals();
            card = null;
            try {
                List<CardTerminal> terminals = cardterminals.list();
                System.out.println("Terminals: " + terminals);
                CardTerminal terminal = cardterminals.getTerminal(terminals.get(0).getName());
                terminal.waitForCardPresent(20000);
                System.out.println("Card detected!");
                card = terminal.connect("*");
                channel = card.getBasicChannel();
                System.out.println(channel);
                return true;
            } catch (Exception e) {
            }
            return false;
        }

        static void disconnect() {
            try {
                channel.close();
                card.disconnect(false);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }

        static byte[] sendCommand(byte[] cmd, CardChannel channel) throws Exception {
            byte []rep = null;
            ResponseAPDU r = null;
            try {
                CommandAPDU apdu = new CommandAPDU(cmd);
                try {
                    System.out.println("APDU Command: " + byteArrayToHexString(apdu.getBytes()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                r = channel.transmit(apdu);
                rep = r.getBytes();
                try {
                    System.out.println("APDU Response: " + byteArrayToHexString(rep));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return rep;
            } catch (CardException e) {
                e.printStackTrace();
            }
            return null;
        }
        static String byteArrayToHexString(byte[] bArray) throws Exception
        {
            if (bArray.length==0)
            {
                throw new Exception();
            }
            StringBuilder sb = new StringBuilder(bArray.length * 2);
            for (byte b : bArray)
            {
                sb.append(String.format("%02X ", b));
            }
            return sb.toString();
        }
*/


    def destroy = {
    }
}
