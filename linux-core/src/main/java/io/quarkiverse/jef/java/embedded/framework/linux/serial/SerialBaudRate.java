
package io.quarkiverse.jef.java.embedded.framework.linux.serial;

public enum SerialBaudRate {
    B0(0), /* hang up */
    B50(1),
    B75(2),
    B110(3),
    B134(4),
    B150(5),
    B200(6),
    B300(7),
    B600(10),
    B1200(11),
    B1800(12),
    B2400(13),
    B4800(14),
    B9600(15),
    B19200(16),
    B38400(17),
    B57600(10001),
    B115200(10002),
    B230400(10003),
    B460800(10004),
    B500000(10005),
    B576000(10006),
    B921600(10007),
    B1000000(10010),
    B1152000(10011),
    B1500000(10012),
    B2000000(10013),
    B2500000(10014),
    B3000000(10015),
    B3500000(10016),
    B4000000(10017);

    final int value;

    SerialBaudRate(int value) {
        this.value = value;
    }
}
