package pp;

import java.math.BigInteger;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by jose on 7/01/17.
 */
public class RSA
{
    private BigInteger p, q, n, r;
    private BigInteger e, d;

    boolean setPQ(String p, String q)
    {
        this.p = new BigInteger(p);
        this.q = new BigInteger(q);
        //System.out.println(p.compareTo("7"));
        if(this.p.compareTo(new BigInteger("7"))<0||this.q.compareTo(new BigInteger("7"))<0)
            return false;
        if (this.p.isProbablePrime(10) && this.q.isProbablePrime(10) && !this.p.equals(this.q))
        {
            setClaves();
            return true;
        }
        return false;
    }

    void generaQP()
    {
        p = new BigInteger(10, 10, new Random());
        q = new BigInteger(10, 10, new Random());
    }

    void setClaves()
    {
        n = p.multiply(q);
        r = p.subtract(BigInteger.valueOf(1));
        r = r.multiply(q.subtract(BigInteger.valueOf(1)));
        e = n.subtract(new BigInteger("1"));
        while (!e.isProbablePrime(10))
            e = e.subtract(new BigInteger("1"));
        d = e.modInverse(r);
    }

    String cifrar(String men)
    {
        String s = "";
        byte b[] = men.getBytes();
        BigInteger c[] = new BigInteger[b.length];
        for (int i = 0; i < b.length; i++)
        {
            c[i] = new BigInteger("" + b[i]);
            s = s + c[i].modPow(e, n) + " ";
        }
        return s;
    }

    String decifrar(String men)
    {
        String s = "";
        StringTokenizer st = new StringTokenizer(men);
        BigInteger b[] = new BigInteger[st.countTokens()];
        for (int i = 0; i < b.length; i++)
        {
            b[i] = new BigInteger(st.nextToken());
            s = s + (char) b[i].modPow(d, n).intValue();
        }
        return s;
    }

    public String getE()
    {
        return "e: " + e;
    }

    public String getD()
    {
        return "d: " + d;
    }

    public String getN()
    {
        return "n: " + n + " p*q";
    }

    public String getR()
    {
        return "r: " + r + " (p-1)*(q-1)";
    }

    public String getP()
    {
        return "" + p;
    }

    public String getQ()
    {
        return "" + q;
    }
}
