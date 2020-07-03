package mk.ukim.finki.emt.carraces.shared_kernel.domain.base;


import org.springframework.lang.Nullable;

import java.io.Serializable;

public interface IdentifiableDomainObject<ID extends Serializable> extends DomainObject{

    @Nullable
    ID id();


}
