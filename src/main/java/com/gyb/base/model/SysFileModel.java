package com.gyb.base.model;
import com.gyb.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Create Time: 2019-01-24 01:18
 */
@Component
public class SysFileModel extends BaseModel{


    /**
    * 显示名称
    */
    @GsExcelProperty(index = 0, description = "显示名称")
    private String sf_show_name;

    /**
    * 本地路径
    */
    @GsExcelProperty(index = 1, description = "本地路径")
    private String sf_norm_local_path;

    /**
    * 网络路径
    */
    @GsExcelProperty(index = 2, description = "网络路径")
    private String sf_norm_url_path;

    /**
    * 文件大小
    */
    @GsExcelProperty(index = 3, description = "文件大小")
    private Long sf_norm_size;

    /**
    * 压缩本地路径
    */
    @GsExcelProperty(index = 4, description = "压缩本地路径")
    private String sf_compress_local_path;

    /**
    * 压缩网络路径
    */
    @GsExcelProperty(index = 5, description = "压缩网络路径")
    private String sf_compress_url_path;

    /**
    * 压缩文件大小
    */
    @GsExcelProperty(index = 6, description = "压缩文件大小")
    private Integer sf_compress_size;

    /**
    * 文件类型
    */
    @GsExcelProperty(index = 7, description = "文件类型")
    private String sf_type;

    public void setSf_show_name(String sf_show_name){        this.sf_show_name=sf_show_name;    }
 
    public String getSf_show_name(){        return sf_show_name;    }
 
    public void setSf_norm_local_path(String sf_norm_local_path){        this.sf_norm_local_path=sf_norm_local_path;    }
 
    public String getSf_norm_local_path(){        return sf_norm_local_path;    }
 
    public void setSf_norm_url_path(String sf_norm_url_path){        this.sf_norm_url_path=sf_norm_url_path;    }
 
    public String getSf_norm_url_path(){        return sf_norm_url_path;    }
 
    public void setSf_norm_size(Long sf_norm_size){        this.sf_norm_size=sf_norm_size;    }
 
    public Long getSf_norm_size(){        return sf_norm_size;    }
 
    public void setSf_compress_local_path(String sf_compress_local_path){        this.sf_compress_local_path=sf_compress_local_path;    }
 
    public String getSf_compress_local_path(){        return sf_compress_local_path;    }
 
    public void setSf_compress_url_path(String sf_compress_url_path){        this.sf_compress_url_path=sf_compress_url_path;    }
 
    public String getSf_compress_url_path(){        return sf_compress_url_path;    }
 
    public void setSf_compress_size(Integer sf_compress_size){        this.sf_compress_size=sf_compress_size;    }
 
    public Integer getSf_compress_size(){        return sf_compress_size;    }
 
    public void setSf_type(String sf_type){        this.sf_type=sf_type;    }
 
    public String getSf_type(){        return sf_type;    }
 
}
