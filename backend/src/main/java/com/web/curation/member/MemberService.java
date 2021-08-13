package com.web.curation.member;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;

import com.web.curation.amazonS3.S3Uploader;
import com.web.curation.error.CustomException;
import com.web.curation.error.ErrorCode;
import com.web.curation.feed.Feed;
import com.web.curation.files.Photo;
import com.web.curation.files.PhotoAndDtoAdapter;
import com.web.curation.files.PhotoDao;
import com.web.curation.files.PhotoDto;
import lombok.AllArgsConstructor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import static com.web.curation.error.ErrorCode.MEMBER_NOT_FOUND;

@AllArgsConstructor
@Service
public class MemberService {

    private MemberDao memberDao;
    private S3Uploader s3Uploader;
    private PhotoDao photoDao;

    public boolean hasSameEmail(String email) {
        Optional<Member> member = memberDao.getMemberByEmail(email);

        if (member.isPresent()) return true;
        return false;
    }

    public MemberDto registMember(MemberDto memberDto) throws IOException {
        memberDto.setPhoto(makeDefaultImg());
        memberDao.save(MemberAdapter.dtoToEntity(memberDto));
        Member member = memberDao.getMemberByEmail(memberDto.getEmail()).get();
        return MemberAdapter.entityToDto(member);
    }

    private Photo makeDefaultImg() throws IOException {
        File file = new File("classpath:/static/img/default.png");
        FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length() , file.getParentFile());

        InputStream input = new FileInputStream(file);
        OutputStream os = fileItem.getOutputStream();
        IOUtils.copy(input, os);
        MultipartFile image = new CommonsMultipartFile(fileItem);

        return insertImage(image);
    }

    public Optional<MemberDto> getUser(String email) {
        Optional<Member> member = memberDao.getMemberByEmail(email);
        Optional<MemberDto> memberDto = Optional.ofNullable(null);
        if (member.isPresent()) {
            memberDto = Optional.of(MemberAdapter.entityToDto(member.get()));
            return memberDto;
        }
        return memberDto;
    }

    public Optional<MemberDto> getUser(String email, String password) {
        Optional<Member> member = memberDao.getMemberByEmailAndPassword(email, password);
        Optional<MemberDto> memberDto = Optional.ofNullable(null);
        if (member.isPresent()) {
            memberDto = Optional.of(MemberAdapter.entityToDto(member.get()));
            return memberDto;
        }
        return memberDto;
    }

    public void certifyMemberByEmail(String email) {
        memberDao.updateAuthentication(email);
    }

    public Optional<MemberDto> getMemberByEmail(String email) {
        Optional<Member> member = memberDao.getMemberByEmail(email);
        Optional<MemberDto> memberDto = Optional.ofNullable(null);
        if (member.isPresent()) {
            memberDto = Optional.of(MemberAdapter.entityToDto(member.get()));
            return memberDto;
        }
        return memberDto;
    }

    public MemberDto updateMember(MemberDto memberDto) throws IOException {
        Member member = memberDao.getMemberByEmail(memberDto.getEmail())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        memberDto.setMemberId(member.getMemberId());
        memberDto.setCreateDate(member.getCreateDate());
        if (memberDto.getImage() != null) {
            memberDto.setPhoto(insertImage(memberDto.getImage()));
        } else {
            memberDto.setPhoto(member.getPhoto());
        }
        memberDao.save(MemberAdapter.dtoToEntity(memberDto));

        return memberDto;
    }

    private Photo insertImage(MultipartFile image) throws IOException {
        PhotoDto uploadPhoto = s3Uploader.upload(image, "static");
        Photo photo = PhotoAndDtoAdapter.dtoToEntity(uploadPhoto);
        return photoDao.save(photo);
    }


}
