package com.omerada.cozumcebinde.core.constant;

public class MessageConstant {
    public static final String ERROR = "ERROR";
	public static final String ERROR_AKTIF = "Aktif mesaj bulunmaktadır.Lütfen aktif mesajı pasife çevirmelisiniz";
    public static final String INFO = "INFO";
	public static final String SUCCESS = "SUCCESS";
	public static final String SAVE_MSG = "Kaydetme işlemi başarıyla gerçekleşti.";
	public static final String UPDATE_MSG = "Güncelleme işlemi başarıyla gerçekleşti.";
	public static final String DELETE_MSG = "Silme işlemi başarıyla gerçekleşti.";
	public static final String EXIST_SIRA_NO = "Sıra zaten kullanılıyor.";

	public static class	VardiyaCizelge{
		public static final String NOT_FOUND = "Vardiya çizelgesi bulunamadı.";
		public static final String NOT_FOUND_BIR_SONRAKI_AY = "Bir sonraki ayın vardiya çizelgesi bulunamadı.";
		public static final String NOT_FOUND_UYGUN_SAAT = "Uygun çalışma saati bulunamadı.";
	}

	public static class	TrafoMerkezi{
		public static final String NOT_FOUND = "Transformatör merkezi bulunamadı.";
	}

	public static class	YgKablo{
		public static final String ALREADY_EXISTS = "Yg kablo bilgileri zaten girilmiş.";
	}

	public static class	IsEmriKontroListesiSoru{
		public static final String NOT_FOUND = "İş emri kontrol listesine ait soru bulunamadı.";
		public static final String ALREADY_EXISTS = "Soru zaten iş emri kontrol listesine eklenmiş.";
	}

	public static class	IsEmri{
		public static final String NOT_FOUND = "İş emri bulunamadı.";
		public static final String KONTROL_LISTESI_ONAY_MSG = "İş emri kontrol listesi onay işlemi başarıyla gerçekleşti.";
	}
}
