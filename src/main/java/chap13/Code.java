package chap13;
import static chap13.Opcode.*;

public class Code {
    protected StoneVM svm;
    protected int codeSize;
    protected int numOfStrings;
    protected int nextReg;
    protected int frameSize;

    public Code(StoneVM stoneVm) {
        svm = stoneVm;
        codeSize = 0;
        numOfStrings = 0;
    }
    public int position() { return codeSize; }
    public void set(short value, int pos) {
        svm.code()[pos] = (byte)(value >>> 8);
        svm.code()[pos + 1] = (byte)value;
    }
    public void add(byte b) {
        svm.code()[codeSize++] = b;
    }
    public void add(short i) {
        add((byte)(i >>> 8));
        add((byte)i);
    }
    public void add(int i) {
        add((byte)(i >>> 24));
        add((byte)(i >>> 16));
        add((byte)(i >>> 8));
        add((byte)i);
    }
    public int record(String s) {
        svm.strings()[numOfStrings] = s;
        return numOfStrings++;
    }

    public void dump(int pos) {
        while (pos < codeSize) {
            System.out.print(String.format("%04d: ", pos));

            switch (svm.code()[pos++]) {
            case 1: {
                int v = StoneVM.readInt(svm.code(), pos);
                pos += 4;
                int r = decodeRegister(svm.code()[pos++]);
                System.out.print("iconst " + v + " r" + r);
                break;
            }

            case 2: {
                int v = (int)svm.code()[pos];
                pos++;
                int r = decodeRegister(svm.code()[pos++]);
                System.out.print("bconst " + v + " r" + r);
                break;
            }

            case 3: {
                int v = StoneVM.readShort(svm.code(), pos);
                pos += 2;
                int r = decodeRegister(svm.code()[pos++]);
                System.out.print("sconst " + v + " r" + r);
                break;
            }

            case 4: {
                byte src = svm.code()[pos];
                byte dest = svm.code()[pos + 1];
                System.out.print("move ");

                if (isRegister(src)) {
                    int r = decodeRegister(src);
                    System.out.print("r" + r + " ");
                } else {
                    int v = (int)src;
                    System.out.print(v + " ");
                }

                if (isRegister(dest)) {
                    int r = decodeRegister(dest);
                    System.out.print("r" + r);
                } else {
                    int v = (int)dest;
                    System.out.print(v);
                }

                pos += 2;
                break;
            }

            case 5: {
                byte rand = svm.code()[pos];
                System.out.print("gmove ");

                if (isRegister(rand)) {
                    int r = decodeRegister(rand);
                    int v = StoneVM.readShort(svm.code(), pos + 1);
                    System.out.print("r" + r + " " + v);
                } else {
                    int v = StoneVM.readShort(svm.code(), pos);
                    int r = decodeRegister(svm.code()[pos + 2]);
                    System.out.print(v + " r" + r);
                }

                pos += 3;
                break;
            }

            case 6: {
                int r = decodeRegister(svm.code()[pos]);
                int offset = StoneVM.readShort(svm.code(), pos + 1);
                System.out.print("ifzero r" + r + " " + offset);

                pos += 3;
                break;
            }

            case 7: {
                int offset = StoneVM.readShort(svm.code(), pos);
                System.out.print("goto " + offset);

                pos += 2;
                break;
            }

            case 8: {
                int r = decodeRegister(svm.code()[pos]);
                int count = (int)svm.code()[pos + 1];
                System.out.print("call r" + r + " " + count);

                pos += 2;
                break;
            }

            case 9: {
                System.out.print("return");
                break;
            }

            case 10: {
                int v = (int)svm.code()[pos];
                System.out.print("save " + v);

                pos += 1;
                break;
            }

            case 11: {
                int v = (int)svm.code()[pos];
                System.out.print("restore " + v);

                pos += 1;
                break;
            }

            case 12: {
                int r = decodeRegister(svm.code()[pos]);
                System.out.print("neg r" + r);

                pos += 1;
                break;
            }

            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20: {
                String[] mnemonic = {"add",
                                     "sub",
                                     "mul",
                                     "div",
                                     "rem",
                                     "equal",
                                     "more",
                                     "less"};

                int r1 = decodeRegister(svm.code()[pos]);
                int r2 = decodeRegister(svm.code()[pos + 1]);
                System.out.print(mnemonic[svm.code()[pos - 1] - 13] + " r" + r1 + " r" + r2);

                pos += 2;
                break;
            }
            }
            System.out.println("");
        }
    }
}
