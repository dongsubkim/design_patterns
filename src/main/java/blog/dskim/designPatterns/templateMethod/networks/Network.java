package blog.dskim.designPatterns.templateMethod.networks;

// Base class of social network.
public abstract class Network {
    String userName;
    String password;

    Network() {}

    // publish the data to whatever network
    public boolean post(String message) {
        // authenticate before posting. Every network uses a different authentication method.
        if (logIn(this.userName, this.password)) {
            // send the post data.
            boolean result = sendDate(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

    protected abstract void logOut();

    protected abstract boolean logIn(String userName, String password);

    protected abstract boolean sendDate(byte[] bytes);
}
