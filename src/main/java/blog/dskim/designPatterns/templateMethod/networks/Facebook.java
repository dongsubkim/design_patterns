package blog.dskim.designPatterns.templateMethod.networks;

// class of social newtork. a concrete social network
public class Facebook extends Network {
    public Facebook(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected void logOut() {
        System.out.println("User: '" + userName + "'was logged out from Facebook");
    }

    @Override
    protected boolean logIn(String userName, String password) {
        System.out.println("\nChecking user's parameters");
        System.out.println("Name: " + userName);
        System.out.print("Password: ");
        for (int i = 0; i < this.password.length(); i++) {
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogin success on Facebook");
        return true;
    }

    private void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 10) {
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean sendDate(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "'was posted on Facebook");
            return true;
        }
        return false;
    }
}
