package mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog;


import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    //izbor t.e uri od kade se zema posleden event(odnosno uri od modulot koj ni ispratil event)
    String source();

    //metod so koj go povikuvame posledniot log od rest servisot od kade mozeme da gi izvleceme site podatoci..
    RemoteEventLog currentLog();

}
