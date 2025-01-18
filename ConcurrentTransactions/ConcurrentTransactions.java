import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized method for deposit
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", New Balance: " + balance);
    }

    // Synchronized method for withdrawal
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to withdraw: " + amount + ", Insufficient Balance: " + balance);
        }
    }
}

public class ConcurrentTransactions {
    public static void main(String[] args) {
        // Create a shared bank account
        BankAccount account = new BankAccount(1000);

        // Define tasks (transactions)
        Runnable depositTask = () -> account.deposit(500);
        Runnable withdrawTask = () -> account.withdraw(300);

        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Simulate 10 concurrent transactions
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                executor.execute(depositTask); // Perform deposit
            } else {
                executor.execute(withdrawTask); // Perform withdrawal
            }
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
