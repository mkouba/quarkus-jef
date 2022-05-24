
package io.quarkiverse.jef.java.embedded.framework.linux.core;

@SuppressWarnings({ "unused", "SameParameterValue" })
public abstract class IoctlBase {
    private static long SPI_IOC_RD_MODE;
    private static long SPI_IOC_WR_MODE;
    private static long SPI_IOC_RD_BITS_PER_WORD;
    private static long SPI_IOC_WR_BITS_PER_WORD;

    private static long SPI_IOC_RD_LSB_FIRST;
    private static long SPI_IOC_WR_LSB_FIRST;

    private static long SPI_IOC_RD_MAX_SPEED_HZ;
    private static long SPI_IOC_WR_MAX_SPEED_HZ;
    private static long SPI_IOC_RD_MODE32;
    private static long SPI_IOC_WR_MODE32;

    private static long GPIO_GET_CHIPINFO_IOCTL;
    private static long GPIO_GET_LINEINFO_IOCTL;
    private static long GPIO_GET_LINEHANDLE_IOCTL;
    private static long GPIO_GET_LINEEVENT_IOCTL;

    private static long GPIOHANDLE_GET_LINE_VALUES_IOCTL;
    private static long GPIOHANDLE_SET_LINE_VALUES_IOCTL;

    public static long getGpioGetChipInfoIoctl() {
        return GPIO_GET_CHIPINFO_IOCTL;
    }

    public static long getGpioGetLineInfoIoctl() {
        return GPIO_GET_LINEINFO_IOCTL;
    }

    public long getSpiIocRdMode() {
        return SPI_IOC_RD_MODE;
    }

    public long getSpiIocWrMode() {
        return SPI_IOC_WR_MODE;
    }

    public long getSpiIocRdBitsPerWord() {
        return SPI_IOC_RD_BITS_PER_WORD;
    }

    public long getSpiIocWrBitsPerWord() {
        return SPI_IOC_WR_BITS_PER_WORD;
    }

    public long getSpiIocRdMaxSpeedHz() {
        return SPI_IOC_RD_MAX_SPEED_HZ;
    }

    public long getSpiIocWrMaxSpeedHz() {
        return SPI_IOC_WR_MAX_SPEED_HZ;
    }

    public long getSpiIocRdMode32() {
        return SPI_IOC_RD_MODE32;
    }

    public long getSpiIocWrMode32() {
        return SPI_IOC_WR_MODE32;
    }

    public long getSpiIocRdLsbFirst() {
        return SPI_IOC_RD_LSB_FIRST;
    }

    public long getSpiIocWrLsbFirst() {
        return SPI_IOC_WR_LSB_FIRST;
    }

    public long getGpioGetLineHandleIoctl() {
        return GPIO_GET_LINEHANDLE_IOCTL;
    }

    public long getGpioGetLineeventIoctl() {
        return GPIO_GET_LINEEVENT_IOCTL;
    }

    public long getGpioHandleGetLineValuesIoctl() {
        return GPIOHANDLE_GET_LINE_VALUES_IOCTL;
    }

    public long getGpioHandleSetLineValuesIoctl() {
        return GPIOHANDLE_SET_LINE_VALUES_IOCTL;
    }

    protected abstract long GET_SPI_IOC_MAGIC();

    protected abstract long IOC_NRBITS();

    protected abstract long IOC_TYPEBITS();

    protected abstract long IOC_SIZEBITS();

    protected abstract long IOC_DIRBITS();

    protected abstract long IOC_NRSHIFT();

    protected abstract long IOC_NONE();

    protected abstract long IOC_READ();

    protected abstract long IOC_WRITE();

    public long SPI_IOC_MESSAGE(int N) {
        return _IOW(GET_SPI_IOC_MAGIC(), 0, SPI_MSGSIZE(N));
    }

    protected abstract int SPI_MSGSIZE(int N);

    private long _IOC_NRMASK() {
        return ((1L << IOC_NRBITS()) - 1);
    }

    private long _IOC_TYPEMASK() {
        return ((1L << IOC_TYPEBITS()) - 1);
    }

    private long _IOC_SIZEMASK() {
        return ((1L << IOC_SIZEBITS()) - 1);
    }

    private long _IOC_DIRMASK() {
        return ((1L << IOC_DIRBITS()) - 1);
    }

    private long _IOC_TYPESHIFT() {
        return IOC_NRSHIFT() + IOC_NRBITS();
    }

    private long _IOC_SIZESHIFT() {
        return _IOC_TYPESHIFT() + IOC_TYPEBITS();
    }

    private long _IOC_DIRSHIFT() {
        return _IOC_SIZESHIFT() + IOC_SIZEBITS();
    }

    private long _IO(long type, long nr) {
        return _IOC(IOC_NONE(), (type), (nr), 0);
    }

    private long _IOR(long type, long nr, long size) {
        return _IOC(IOC_READ(), type, nr, size);
    }

    private long _IOW(long type, long nr, long size) {
        return _IOC(IOC_WRITE(), type, nr, size);
    }

    private long _IOWR(long type, long nr, long size) {
        return _IOC(IOC_READ() | IOC_WRITE(), type, nr, size);
    }

    private long _IOC(long dir, long type, long nr, long size) {
        return (((dir) << _IOC_DIRSHIFT()) |
                ((type) << _IOC_TYPESHIFT()) |
                ((nr) << IOC_NRSHIFT()) |
                ((size) << _IOC_SIZESHIFT()));
    }

    protected void initVariables() {
        SPI_IOC_RD_MODE = _IOR(GET_SPI_IOC_MAGIC(), 1, 1);
        SPI_IOC_WR_MODE = _IOW(GET_SPI_IOC_MAGIC(), 1, 1);
        SPI_IOC_RD_BITS_PER_WORD = _IOR(GET_SPI_IOC_MAGIC(), 3, 1);
        SPI_IOC_WR_BITS_PER_WORD = _IOW(GET_SPI_IOC_MAGIC(), 3, 1);
        SPI_IOC_RD_MAX_SPEED_HZ = _IOR(GET_SPI_IOC_MAGIC(), 4, 4);
        SPI_IOC_WR_MAX_SPEED_HZ = _IOW(GET_SPI_IOC_MAGIC(), 4, 4);
        SPI_IOC_RD_MODE32 = _IOR(GET_SPI_IOC_MAGIC(), 5, 4);
        SPI_IOC_WR_MODE32 = _IOW(GET_SPI_IOC_MAGIC(), 5, 4);
        SPI_IOC_RD_LSB_FIRST = _IOR(GET_SPI_IOC_MAGIC(), 2, 1);
        SPI_IOC_WR_LSB_FIRST = _IOW(GET_SPI_IOC_MAGIC(), 2, 1);

        GPIO_GET_CHIPINFO_IOCTL = _IOR(0xB4, 0x01, 68);
        GPIO_GET_LINEINFO_IOCTL = _IOWR(0xB4, 0x02, 72);
        GPIO_GET_LINEHANDLE_IOCTL = _IOWR(0xB4, 0x03, 364);
        GPIO_GET_LINEEVENT_IOCTL = _IOWR(0xB4, 0x04, 48);

        GPIOHANDLE_GET_LINE_VALUES_IOCTL = _IOWR(0xB4, 0x08, 64);
        GPIOHANDLE_SET_LINE_VALUES_IOCTL = _IOWR(0xB4, 0x09, 64);
    }
}
