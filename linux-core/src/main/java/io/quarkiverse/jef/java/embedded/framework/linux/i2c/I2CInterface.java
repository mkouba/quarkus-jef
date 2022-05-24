
package io.quarkiverse.jef.java.embedded.framework.linux.i2c;

import static io.quarkiverse.jef.java.embedded.framework.linux.core.util.StringUtils.dump;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.quarkiverse.jef.java.embedded.framework.linux.core.Fcntl;
import io.quarkiverse.jef.java.embedded.framework.linux.core.LinuxUtils;
import io.quarkiverse.jef.java.embedded.framework.linux.core.NativeIOException;
import io.quarkiverse.jef.java.embedded.framework.linux.core.io.FileHandle;
import io.quarkiverse.jef.java.embedded.framework.linux.core.util.StringUtils;

/**
 * I2CInterface represent auto-selecting functionality for the I2C device and provides
 * ability to read/write operation over I2C bus or {@link SMBus} interface
 */
public class I2CInterface {
    private static final Logger log = Logger.getLogger(I2CInterface.class.getName());

    private final I2CBus bus;
    private final SMBus smBus;
    private final FileHandle fd;
    private final int address;

    /**
     * Allocate new instance of I2C Interface
     * 
     * @param bus parent {@link I2CBus}
     * @param fd {@link FileHandle} to used {@link I2CBus}
     * @param address address of device
     */
    I2CInterface(I2CBus bus, FileHandle fd, int address) {
        log.log(
                Level.FINEST,
                () -> String.format(
                        "create I2C Interface for bus '%s' file id '%d' and address '%d'",
                        bus.getPath(), fd.getHandle(), address));

        this.bus = bus;
        this.fd = fd;
        this.address = address;
        this.smBus = new SMBus(this);
    }

    /**
     * Returns {@link SMBus} interface for current I2C device
     * 
     * @return SMBus interface
     */
    public SMBus getSmBus() {
        return smBus;
    }

    /**
     * Reads data from I2C device to buffer capacity
     * 
     * @param buf allocated buffer
     * @throws NativeIOException if I2C bus reject command
     */
    public void read(ByteBuffer buf) throws NativeIOException {
        read(buf, buf.capacity());
    }

    /**
     * Reads data from I2C device to buffer capacity
     * 
     * @param buf allocated buffer
     * @param length number of bytes to read
     * @throws NativeIOException if I2C bus reject command
     */
    public void read(ByteBuffer buf, int length) throws NativeIOException {
        log.log(
                Level.FINEST,
                () -> String.format(
                        "reading '%d' bytes from bus '%s'",
                        length, bus.getPath()));
        synchronized (synchLock()) {
            synchSelect();
            Fcntl.getInstance().read(getFD(), LinuxUtils.toBytes(buf), length);
        }
    }

    /**
     * Writes data from buffer to selected I2C device
     * 
     * @param buf buffer with data
     * @throws NativeIOException if I2C bus reject command
     */
    public void write(ByteBuffer buf) throws NativeIOException {
        write(buf, buf.capacity());
    }

    /**
     * Writes data from buffer to selected I2C device
     * 
     * @param buf buffer with data
     * @param length amount of data to write
     * @throws NativeIOException if I2C bus reject command
     */
    public void write(ByteBuffer buf, int length) throws NativeIOException {
        log.log(
                Level.FINEST,
                () -> String.format(
                        "write '%d' bytes to bus '%s'",
                        length, bus.getPath()));
        log.log(Level.FINEST, StringUtils.dump(buf));

        synchronized (synchLock()) {
            synchSelect();
            Fcntl.getInstance().write(getFD(), LinuxUtils.toBytes(buf), length);
        }
    }

    /**
     * Lock decorator for selected device synchronization
     * 
     * @return lock object
     */
    Object synchLock() {
        return bus;
    }

    /**
     * Selecting current device in I2C bus if it's changed
     * 
     * @throws NativeIOException if I2C bus reject command
     */
    void synchSelect() throws NativeIOException {
        bus.selectSlave(address, false);
    }

    /**
     * Returns {@link FileHandle} to I2C bus
     * 
     * @return file handle to current I2C bus
     */
    FileHandle getFD() {
        return fd;
    }

    /**
     * Returns path to I2C device
     * 
     * @return path to I2C device
     */
    String getPath() {
        return bus.getPath();
    }

    /**
     * Returns parent {@link I2CBus} for current interface
     * 
     * @return parent I2C bus
     */
    public I2CBus getI2CBus() {
        return bus;
    }

    /**
     * Returns I2C address for current interface device
     * 
     * @return i2c address of device
     */
    public int getAddress() {
        return address;
    }
}
