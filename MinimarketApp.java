import java.util.InputMismatchException;
import java.util.Scanner;

// Interface untuk menghitung total bayar
interface CalculateTotal {
    double calculateTotal();
}

// Kelas dasar untuk barang
class Barang {
    protected String namaBarang;
    protected double hargaBarang;
    protected int jumlahBarang;

    public Barang(String namaBarang, double hargaBarang, int jumlahBarang) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBarang = jumlahBarang;
    }

    public void displayInfo() {
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Harga Barang: " + hargaBarang);
        System.out.println("Jumlah Barang: " + jumlahBarang);
    }
}

// Kelas turunan untuk barang dengan diskon
class BarangDiskon extends Barang implements CalculateTotal {
    private double diskon;

    public BarangDiskon(String namaBarang, double hargaBarang, int jumlahBarang, double diskon) {
        super(namaBarang, hargaBarang, jumlahBarang);
        this.diskon = diskon;
    }

    @Override
    public double calculateTotal() {
        double total = hargaBarang * jumlahBarang;
        return total - (total * diskon);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Diskon: " + (diskon * 100) + "%");
    }
}

// Exception untuk jumlah barang yang tidak valid
class InvalidJumlahBarangException extends Exception {
    public InvalidJumlahBarangException(String message) {
        super(message);
    }
}

// Driver Class
public class MinimarketApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("No. Faktur: ");
            String noFaktur = scanner.next();

            System.out.print("Nama Pelanggan: ");
            String namaPelanggan = scanner.next();

            System.out.print("Nama Barang: ");
            String namaBarang = scanner.next();

            System.out.print("Harga Barang: ");
            double hargaBarang = scanner.nextDouble();

            System.out.print("Jumlah Barang: ");
            int jumlahBarang = scanner.nextInt();
            if (jumlahBarang <= 0) {
                throw new InvalidJumlahBarangException("Jumlah barang harus lebih dari 0.");
            }

            System.out.print("Total Bayar: ");
            double totalBayar = scanner.nextDouble();

            // Contoh penggunaan kelas BarangDiskon
            BarangDiskon barangDiskon = new BarangDiskon(namaBarang, hargaBarang, jumlahBarang, 0.1);
            barangDiskon.displayInfo();
            System.out.println("Total Bayar (setelah diskon): " + barangDiskon.calculateTotal());
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Pastikan input sesuai dengan tipe data yang diminta.");
        } catch (InvalidJumlahBarangException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
