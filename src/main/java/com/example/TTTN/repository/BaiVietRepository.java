package com.example.TTTN.repository;

import com.example.TTTN.dto.BaiVietDTO;
import com.example.TTTN.entity.Album;
import com.example.TTTN.entity.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BaiVietRepository extends JpaRepository<BaiViet,Integer> {
    @Query("""
    SELECT new com.example.TTTN.dto.BaiVietDTO(bv.id, bv.tieuDe, cd.ten, bv.noiDung, bv.createdAt, bv.nguoiTao.ten, dvb.id, bv.trangThai) 
    FROM BaiViet bv
    join DotVietBai dvb on bv.dotVietBai.id = dvb.id
    join DotDangKy ddk on dvb.dotDangKy.id = ddk.id
    join ChuDe cd on ddk.chuDe.id = cd.id
""")
    Page<BaiVietDTO> findBaiViet(Pageable pageable);

    @Query("""
    SELECT new com.example.TTTN.dto.BaiVietDTO(bv.id, bv.tieuDe, cd.ten, bv.noiDung, bv.createdAt, bv.nguoiTao.ten, dvb.id, bv.trangThai) 
    FROM BaiViet bv
    join DotVietBai dvb on bv.dotVietBai.id = dvb.id
    join DotDangKy ddk on dvb.dotDangKy.id = ddk.id
    join ChuDe cd on ddk.chuDe.id = cd.id
""")
    List<BaiVietDTO> findBaiViet1();

    @Query("""
    SELECT new com.example.TTTN.dto.BaiVietDTO(bv.id, bv.tieuDe, cd.ten, bv.noiDung, bv.createdAt, bv.nguoiTao.ten, dvb.id, bv.trangThai) 
    FROM BaiViet bv
    join DotVietBai dvb on bv.dotVietBai.id = dvb.id
    join DotDangKy ddk on dvb.dotDangKy.id = ddk.id
    join ChuDe cd on ddk.chuDe.id = cd.id
    where bv.id=:id
""")
    BaiVietDTO findBaiVietById(Integer id);

    @Query("""
    SELECT new com.example.TTTN.dto.BaiVietDTO(bv.id, bv.tieuDe, cd.ten, bv.noiDung, bv.createdAt, bv.nguoiTao.ten, dvb.id, bv.trangThai) 
    FROM BaiViet bv
    JOIN DotVietBai dvb ON bv.dotVietBai.id = dvb.id
    JOIN DotDangKy ddk ON dvb.dotDangKy.id = ddk.id
    JOIN ChuDe cd ON ddk.chuDe.id = cd.id
    WHERE (:tieuDe IS NULL OR bv.tieuDe LIKE %:tieuDe%)
    AND (:chuDe IS NULL OR cd.ten LIKE %:chuDe%)
    AND (:dotVietBaiId IS NULL OR dvb.id = :dotVietBaiId)
""")
    List<BaiVietDTO> findBaiVietByFilters(
            @Param("tieuDe") String tieuDe,
            @Param("chuDe") String chuDe,
            @Param("dotVietBaiId") Integer dotVietBaiId
    );

    @Query("""
    SELECT new com.example.TTTN.dto.BaiVietDTO(bv.id, bv.tieuDe, cd.ten, bv.noiDung, bv.createdAt, bv.nguoiTao.ten, dvb.id, bv.trangThai) 
    FROM BaiViet bv
    JOIN DotVietBai dvb ON bv.dotVietBai.id = dvb.id
    JOIN DotDangKy ddk ON dvb.dotDangKy.id = ddk.id
    JOIN ChuDe cd ON ddk.chuDe.id = cd.id
    WHERE (:tieuDe IS NULL OR bv.tieuDe LIKE %:tieuDe%)
    AND (:chuDe IS NULL OR cd.ten LIKE %:chuDe%)
    AND (:dotVietBaiId IS NULL OR dvb.id = :dotVietBaiId)
""")
    Page<BaiVietDTO> findBaiVietByFilters1(
            Pageable pageable,
            @Param("tieuDe") String tieuDe,
            @Param("chuDe") String chuDe,
            @Param("dotVietBaiId") Integer dotVietBaiId
    );

    @Query("""
    SELECT COUNT(*)
    FROM BaiViet bv
    WHERE bv.nguoiTao.id = :id
""")
    Long tongBaiViet(@Param("id") Integer id);

    @Query("""
    SELECT COUNT(*)
    FROM BaiViet bv
    WHERE bv.nguoiTao.id = :id
    AND bv.trangThai = :trangThai
""")
    Long baiVietTrangThai(@Param("id") Integer id, @Param("trangThai") String trangThai);

    @Query("""
    SELECT new com.example.TTTN.dto.BaiVietDTO(bv.id, bv.tieuDe, cd.ten, bv.noiDung, bv.createdAt, bv.nguoiTao.ten, dvb.id, bv.trangThai) 
    FROM BaiViet bv
    join DotVietBai dvb on bv.dotVietBai.id = dvb.id
    join DotDangKy ddk on dvb.dotDangKy.id = ddk.id
    join ChuDe cd on ddk.chuDe.id = cd.id
    WHERE bv.nguoiTao.id = :id
    AND (:trangThai IS NULL OR bv.trangThai = :trangThai)
""")
    List<BaiVietDTO> baiVietCuaToiTrangThai(@Param("id") Integer id, @Param("trangThai") String trangThai);
}
