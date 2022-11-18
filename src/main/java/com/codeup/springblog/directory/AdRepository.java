package com.codeup.springblog.directory;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository <Ad, Long>{
}
