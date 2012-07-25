// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.broadcast.helper.MtvUtilDebug;
import android.os.SystemProperties;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

public class MtvUtilCrypto
{

    public MtvUtilCrypto(int i)
    {
        mCipher = null;
        mEncKey = null;
        mIV = null;
        mIB = null;
        mCipherIV = null;
        mEncType = 0;
        mOpMode = 0;
        mCipherOutSize = 0;
        MtvUtilDebug.Mid("MtvUtilCrypto", "MtvUtilCrypto: Entered");
        mOpMode = i;
        if(mCipher == null)
            try
            {
                MtvUtilDebug.Mid("MtvUtilCrypto", "MtvUtilCrypto: getting instance of cipher");
                mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            catch(NoSuchAlgorithmException nosuchalgorithmexception)
            {
                nosuchalgorithmexception.printStackTrace();
            }
            catch(NoSuchPaddingException nosuchpaddingexception)
            {
                nosuchpaddingexception.printStackTrace();
            }
        mEncType = 0;
        generateIB();
        generateEncKey();
        MtvUtilDebug.Mid("MtvUtilCrypto", "MtvUtilCrypto: Exit");
    }

    private void extractIV(ByteBuffer bytebuffer)
    {
        MtvUtilDebug.Mid("MtvUtilCrypto", "extractIV: Entered ");
        bytebuffer.position(16);
        byte abyte0[] = new byte[16];
        bytebuffer.get(abyte0);
        mCipherIV = new IvParameterSpec(abyte0);
        MtvUtilDebug.Mid("MtvUtilCrypto", "extractIV: Exit");
    }

    private void generateEncKey()
    {
        MtvUtilDebug.Mid("MtvUtilCrypto", "generateEncKey: Enterd");
        if(mEncKey != null) goto _L2; else goto _L1
_L1:
        byte abyte0[][];
        byte abyte7[];
        byte abyte8[];
        byte abyte9[];
        KeyGenerator keygenerator;
        abyte0 = new byte[6][];
        byte abyte1[] = new byte[16];
        abyte1[0] = 122;
        abyte1[1] = 54;
        abyte1[2] = 9;
        abyte1[3] = 28;
        abyte1[4] = 121;
        abyte1[5] = 68;
        abyte1[6] = 97;
        abyte1[7] = 60;
        abyte1[8] = 98;
        abyte1[9] = 94;
        abyte1[10] = 121;
        abyte1[11] = 63;
        abyte1[12] = 35;
        abyte1[13] = 87;
        abyte1[14] = 117;
        abyte1[15] = 21;
        abyte0[0] = abyte1;
        byte abyte2[] = new byte[16];
        abyte2[0] = 48;
        abyte2[1] = 81;
        abyte2[2] = 78;
        abyte2[3] = 89;
        abyte2[4] = 40;
        abyte2[5] = 115;
        abyte2[6] = 18;
        abyte2[7] = 12;
        abyte2[8] = 27;
        abyte2[9] = 107;
        abyte2[10] = 48;
        abyte2[11] = 13;
        abyte2[12] = 123;
        abyte2[13] = 106;
        abyte2[14] = 23;
        abyte2[15] = 78;
        abyte0[1] = abyte2;
        byte abyte3[] = new byte[16];
        abyte3[0] = 109;
        abyte3[1] = 20;
        abyte3[2] = 99;
        abyte3[3] = 29;
        abyte3[4] = 59;
        abyte3[5] = 64;
        abyte3[6] = 86;
        abyte3[7] = 126;
        abyte3[8] = 115;
        abyte3[9] = 94;
        abyte3[10] = 92;
        abyte3[11] = 51;
        abyte3[12] = 107;
        abyte3[13] = 61;
        abyte3[14] = 93;
        abyte3[15] = 90;
        abyte0[2] = abyte3;
        byte abyte4[] = new byte[16];
        abyte4[0] = 18;
        abyte4[1] = 88;
        abyte4[2] = 111;
        abyte4[3] = 34;
        abyte4[4] = 92;
        abyte4[5] = 85;
        abyte4[6] = 32;
        abyte4[7] = 59;
        abyte4[8] = 111;
        abyte4[9] = 33;
        abyte4[10] = 63;
        abyte4[11] = 70;
        abyte4[12] = 115;
        abyte4[13] = 1;
        abyte4[14] = 72;
        abyte4[15] = 62;
        abyte0[3] = abyte4;
        byte abyte5[] = new byte[16];
        abyte5[0] = 27;
        abyte5[1] = 61;
        abyte5[2] = 112;
        abyte5[3] = 52;
        abyte5[4] = 43;
        abyte5[5] = 124;
        abyte5[6] = 81;
        abyte5[7] = 79;
        abyte5[8] = 39;
        abyte5[9] = 109;
        abyte5[10] = 78;
        abyte5[11] = 5;
        abyte5[12] = 0;
        abyte5[13] = 106;
        abyte5[14] = 35;
        abyte5[15] = 126;
        abyte0[4] = abyte5;
        byte abyte6[] = new byte[16];
        abyte6[0] = 112;
        abyte6[1] = 85;
        abyte6[2] = 19;
        abyte6[3] = 112;
        abyte6[4] = 53;
        abyte6[5] = 73;
        abyte6[6] = 32;
        abyte6[7] = 87;
        abyte6[8] = 121;
        abyte6[9] = 115;
        abyte6[10] = 73;
        abyte6[11] = 104;
        abyte6[12] = 103;
        abyte6[13] = 5;
        abyte6[14] = 71;
        abyte6[15] = 61;
        abyte0[5] = abyte6;
        abyte7 = new byte[16];
        abyte8 = new byte[16];
        abyte9 = new byte[16];
        keygenerator = null;
        MtvUtilDebug.Mid("MtvUtilCrypto", "generateEncKey: generating key");
        mEncType;
        JVM INSTR tableswitch 0 4: default 752
    //                   0 752
    //                   1 859
    //                   2 899
    //                   3 939
    //                   4 1019;
           goto _L3 _L3 _L4 _L5 _L6 _L7
_L3:
        for(int k = 0; k < 16; k++)
        {
            abyte7[k] = (byte)(abyte0[0][k] ^ abyte0[2][k]);
            abyte7[k] = (byte)(abyte7[k] + abyte0[4][k]);
            abyte8[k] = (byte)(abyte0[1][k] ^ abyte0[3][k]);
            abyte8[k] = (byte)(abyte8[k] + abyte0[5][k]);
            abyte9[k] = (byte)(abyte7[k] ^ abyte8[k]);
        }

        break; /* Loop/switch isn't completed */
_L4:
        byte abyte15[] = SystemProperties.get("ril.IMEI", "INVALID").getBytes();
        int k1 = 0;
        while(k1 < abyte15.length) 
        {
            abyte0[0][k1] = abyte15[k1];
            k1++;
        }
        if(true) goto _L3; else goto _L8
_L8:
        break; /* Loop/switch isn't completed */
_L5:
        byte abyte14[] = SystemProperties.get("ril.IMSI", "INVALID").getBytes();
        int j1 = 0;
        while(j1 < abyte14.length) 
        {
            abyte0[1][j1] = abyte14[j1];
            j1++;
        }
        if(true) goto _L3; else goto _L9
_L9:
        break; /* Loop/switch isn't completed */
_L6:
        byte abyte12[] = SystemProperties.get("ril.IMEI", "INVALID").getBytes();
        for(int l = 0; l < abyte12.length; l++)
            abyte0[0][l] = abyte12[l];

        byte abyte13[] = SystemProperties.get("ril.IMSI", "INVALID").getBytes();
        int i1 = 0;
        while(i1 < abyte13.length) 
        {
            abyte0[1][i1] = abyte13[i1];
            i1++;
        }
        if(true) goto _L3; else goto _L10
_L10:
        break; /* Loop/switch isn't completed */
_L7:
        byte abyte10[] = SystemProperties.get("ril.IMEI", "INVALID").getBytes();
        for(int i = 0; i < abyte10.length; i++)
            abyte0[0][i] = abyte10[i];

        byte abyte11[] = SystemProperties.get("ril.bt_macaddr", "INVALID").getBytes();
        int j = 0;
        while(j < abyte11.length) 
        {
            abyte0[1][j] = abyte11[j];
            j++;
        }
        if(true) goto _L3; else goto _L11
_L11:
        KeyGenerator keygenerator1 = KeyGenerator.getInstance("AES");
        keygenerator = keygenerator1;
_L13:
        keygenerator.init(128, new SecureRandom(abyte9));
        mEncKey = keygenerator.generateKey();
_L2:
        MtvUtilDebug.Mid("MtvUtilCrypto", "generateEncKey: Exit");
        return;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        nosuchalgorithmexception.printStackTrace();
        if(true) goto _L13; else goto _L12
_L12:
    }

    private void generateIB()
    {
        MtvUtilDebug.Mid("MtvUtilCrypto", "generateIB: Enterd");
        mIB = new byte[16];
        generateRandomBytes(mIB);
        mIB[0] = 1;
        mIB[1] = 0;
        mIB[2] = mEncType;
        mIB[3] = 1;
    }

    private void generateIV()
    {
        MtvUtilDebug.Mid("MtvUtilCrypto", "generateIV: Enterd");
        if(mIV == null || mCipherIV == null)
        {
            MtvUtilDebug.Mid("MtvUtilCrypto", "generateIV: generarting IV");
            mIV = new byte[16];
            generateRandomBytes(mIV);
            mCipherIV = new IvParameterSpec(mIV);
        }
    }

    private void generateRandomBytes(byte abyte0[])
    {
        (new SecureRandom()).nextBytes(abyte0);
    }

    private void setMode(int i)
    {
        MtvUtilDebug.Mid("MtvUtilCrypto", (new StringBuilder()).append("setMode: Entered opmode is ").append(i).toString());
        try
        {
            mCipher.init(i, mEncKey, mCipherIV);
        }
        catch(InvalidKeyException invalidkeyexception)
        {
            invalidkeyexception.printStackTrace();
        }
        catch(InvalidAlgorithmParameterException invalidalgorithmparameterexception)
        {
            invalidalgorithmparameterexception.printStackTrace();
        }
        MtvUtilDebug.Mid("MtvUtilCrypto", "setMode: Exit");
    }

    public int decryptData(ByteBuffer bytebuffer, ByteBuffer bytebuffer1)
    {
        int i;
        MtvUtilDebug.Mid("MtvUtilCrypto", "decryptData: Entered");
        i = 0;
        if(bytebuffer == null)
            break MISSING_BLOCK_LABEL_53;
        MtvUtilDebug.Mid("MtvUtilCrypto", "decryptData: Decrypting data");
        int j;
        extractIV(bytebuffer);
        setMode(2);
        bytebuffer.position(32);
        j = mCipher.doFinal(bytebuffer, bytebuffer1);
        i = j;
_L2:
        MtvUtilDebug.Mid("MtvUtilCrypto", (new StringBuilder()).append("decryptData: exit with len = ").append(i).toString());
        return i;
        ShortBufferException shortbufferexception;
        shortbufferexception;
        shortbufferexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        IllegalBlockSizeException illegalblocksizeexception;
        illegalblocksizeexception;
        illegalblocksizeexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        BadPaddingException badpaddingexception;
        badpaddingexception;
        badpaddingexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int encryptData(ByteBuffer bytebuffer, ByteBuffer bytebuffer1)
    {
        MtvUtilDebug.Mid("MtvUtilCrypto", "encryptData: Entered");
        ByteBuffer bytebuffer2 = ByteBuffer.allocate(mCipherOutSize);
        if(bytebuffer != null)
            try
            {
                MtvUtilDebug.Mid("MtvUtilCrypto", "encryptData: encrypting");
                generateIV();
                setMode(1);
                mCipher.doFinal(bytebuffer, bytebuffer2);
            }
            catch(ShortBufferException shortbufferexception)
            {
                shortbufferexception.printStackTrace();
            }
            catch(IllegalBlockSizeException illegalblocksizeexception)
            {
                illegalblocksizeexception.printStackTrace();
            }
            catch(BadPaddingException badpaddingexception)
            {
                badpaddingexception.printStackTrace();
            }
        bytebuffer1.put(mIB);
        bytebuffer1.put(mIV);
        bytebuffer1.put(bytebuffer2.array());
        MtvUtilDebug.Mid("MtvUtilCrypto", (new StringBuilder()).append("encryptData: Exit with len = ").append(0).toString());
        return 0;
    }

    public final int getOutputSize(int i, int j)
    {
        MtvUtilDebug.Mid("MtvUtilCrypto", "getOutputSize: Entered");
        generateIV();
        int k;
        try
        {
            MtvUtilDebug.Mid("MtvUtilCrypto", "getOutputSize: Entered");
            mCipher.init(i, mEncKey, mCipherIV);
        }
        catch(InvalidKeyException invalidkeyexception)
        {
            invalidkeyexception.printStackTrace();
        }
        catch(InvalidAlgorithmParameterException invalidalgorithmparameterexception)
        {
            invalidalgorithmparameterexception.printStackTrace();
        }
        if(1 == mOpMode)
        {
            mCipherOutSize = mCipher.getOutputSize(j);
            k = 32 + mCipherOutSize;
        } else
        {
            mCipherOutSize = mCipher.getOutputSize(j - 32);
            k = mCipherOutSize;
        }
        return k;
    }

    private Cipher mCipher;
    private AlgorithmParameterSpec mCipherIV;
    private int mCipherOutSize;
    private Key mEncKey;
    private byte mEncType;
    private byte mIB[];
    private byte mIV[];
    private int mOpMode;
}
