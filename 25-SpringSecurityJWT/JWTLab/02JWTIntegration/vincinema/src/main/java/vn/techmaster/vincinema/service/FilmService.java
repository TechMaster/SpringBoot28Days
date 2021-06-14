package vn.techmaster.vincinema.service;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.vincinema.model.Film;
import vn.techmaster.vincinema.repository.FilmRepository;


@Service
public class FilmService {
  @PersistenceContext
  private EntityManager em;


  @Autowired private  FilmRepository filmRepo;

  @Transactional
  public void generateSampleFilms() throws ParseException {

    Film banTayDietQuy = Film.builder()
    .title("BÀN TAY DIỆT QUỶ")
    .description("Sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – tên quỷ Satan đột lốt người. Từ đó sự thật về cái chết của cha Yong Hoo cũng dần được hé lộ cũng như nguyên nhân anh trở thành “người được chọn”.")    
    .build();


    Film palmSpring = Film.builder()
    .title("PALM SPRINGS: MỞ MẮT THẤY HÔM QUA")
    .description("Mở Mắt Thấy Hôm Qua (tựa gốc: Palm Springs) – đúng như tên gọi, bộ phim là một vòng lặp bất tận của thời gian, với thật nhiều những rắc rối lặp đi lặp lại không có điểm dừng. Anh chàng Nyles (Andy Samberg) và nàng phù dâu bất đắc dĩ Sarah (Cristin Milioti) tình cờ gặp nhau tại đám cưới ở Palm Springs, mọi thứ trở nên phức tạp khi Nyles và Sarah “mắc kẹt” mãi ở ngày vui của người khác. Trong khi Sarah điên cuồng tìm cách thoát ra thì Nyles bình thản chấp nhận sống lại ngày hôm qua thêm một lần nữa. Họ sẽ làm gì để có thể thoát khỏi nơi này, thoát khỏi những vấn đề của chính mình khi giờ đây còn “vướng” phải nhau nữa?")
    .build();



    Film trumCuoiSieuDang = Film.builder()
    .title("TRÙM CUỐI SIÊU ĐẲNG")
    .description("Mắc kẹt trong một vòng lặp thời gian ngay đúng ngày anh ta bị giết chết, một cựu đặc vụ Roy Pulver (Frank Gillo) đã phát hiện ra manh mới về một dự án bí mật của chính phủ có thể giải đáp bí ẩn đằng sau cái chết vô thời hạn của anh ta. Roy buộc lòng phải chạy đua với thời gian và truy bắt tên Colonel Ventor (Mel Gibson), đầu sỏ của dự án chính phủ này. Trong lúc đó, anh phải thoát khỏi cuộc vây bắt của những tên sát thủ tàn ác quyết tâm ngăn cản Roy tìm ra được sự thật. Liệu Roy Pulver có thể thoát khỏi vòng lặp này và cứu lấy gia đình đồng thời sống lại một lần nữa vào ngày mai?")
    .build();


    Film cucNoHoaCucCung = Film.builder()
    .title("CỤC NỢ HÓA CỤC CƯNG")
    .description("Du-seok (Sung Dong Il) và Jong-bae (Kim Hiewon) là hai gã chuyên đòi nợ thuê có máu mặt. Để uy hiếp một con nợ, cả hai đã bắt Seung-yi (Park Soi) - một bé gái 9 tuổi làm vật thế chấp cho số nợ của mẹ cô bé. Tuy nhiên, mẹ của Seung-yi lại bị trục xuất về nước, và hai ông chú đành nhận trách nhiệm trông chừng Seung-yi đến khi cô bé được một gia đình giàu có nhận nuôi. Khi phát hiện ra Seung-yi nhỏ bé bị bán đi làm công cho một bà chủ vô trách nhiệm, Du-seok đã tìm đến để chuộc lại cô bé. Mặc dù Seung-yi vốn là \"cục nợ\" Du-seok và Jong-bae không hề mong muốn, cô bé dần trở thành cục cưng yêu quý và cả 3 sống bên nhau như một gia đình.")
    .build();

    em.persist(banTayDietQuy);
    em.persist(palmSpring);
    em.persist(trumCuoiSieuDang);
    em.persist(cucNoHoaCucCung);
  }

  public List<Film> findAll() {
    return filmRepo.findAll();
  }
}
