package com.example.e_commerce_rest_api.utils.config;

import com.example.e_commerce_rest_api.entity.Agreement;
import com.example.e_commerce_rest_api.entity.Culture;
import com.example.e_commerce_rest_api.entity.Role;
import com.example.e_commerce_rest_api.entity.User;
import com.example.e_commerce_rest_api.enums.AgreementType;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.enums.RoleType;
import com.example.e_commerce_rest_api.model.response.AgreementDto;
import com.example.e_commerce_rest_api.repository.AgreementRepository;
import com.example.e_commerce_rest_api.repository.CultureRepository;
import com.example.e_commerce_rest_api.repository.RoleRepository;
import com.example.e_commerce_rest_api.repository.UserRepository;
import com.example.e_commerce_rest_api.service.impl.AgreementService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSourceHelper messageSourceHelper;
    private final AgreementService agreementService;
    private final AgreementRepository agreementRepository;
    private final ModelMapper modelMapper;
    private final CultureRepository cultureRepository;

    @Override
    public void run(ApplicationArguments args) {
        addCultures();
        addAgreements();
        addRoles();
        addUsers();
    }

    private void addCultures() {
        Culture cultureTr = Culture.builder()
                .name("tr-TR")
                .title("Türkçe")
                .build();

        Culture cultureEn = Culture.builder()
                .name("en-EN")
                .title("English")
                .build();
        List<Culture> cultures = cultureRepository.findAll();
        if (cultures.isEmpty()) cultureRepository.saveAll(List.of(cultureTr, cultureEn));
    }

    private void addAgreements() {
        Agreement privacyAgreementTr = Agreement.builder()
                .version(1)
                .cultureName("tr-TR")
                .content("<section><h1>Gizlilik Politikası</h1><p><strong>Sürüm:</strong> 1.0</p><p><strong>Tarih:</strong> 25.06.2025</p><h2>1. Veri Toplama</h2><p>X Projesi, kullanıcıdan ad, soyad, telefon numarası gibi kişisel verileri yalnızca hizmet sunmak amacıyla toplar.</p><h2>2. Veri Kullanımı</h2><p>Toplanan veriler aşağıdaki amaçlarla kullanılır:</p><ul><li>Hizmet sunumu</li><li>Hesap güvenliği</li><li>Geri bildirim ve destek süreçleri</li></ul><h2>3. Veri Paylaşımı</h2><p>Kullanıcı verileri, yasal yükümlülükler dışında üçüncü kişilerle paylaşılmaz.</p><h2>4. Veri Güvenliği</h2><p>Kişisel veriler, teknik ve idari tedbirlerle korunur. Ancak çevrimiçi sistemlerde %100 güvenlik garanti edilemez.</p><h2>5. Saklama Süresi</h2><p>Kişisel veriler, ilgili mevzuat çerçevesinde gerekli süre boyunca saklanır, ardından silinir veya anonimleştirilir.</p><h2>6. Haklarınız</h2><p>Kullanıcı olarak, KVKK kapsamındaki haklarınızı kullanmak için bizimle iletişime geçebilirsiniz: <a href=\"mailto:kvkk@xprojesi.com\">kvkk@xprojesi.com</a></p></section>")
                .type(AgreementType.PRIVACY_AGREEMENT)
                .build();

        Agreement userAgreementTr = Agreement.builder()
                .version(1)
                .cultureName("tr-TR")
                .content("<section><h1>Kullanıcı Sözleşmesi</h1><p><strong>Sürüm:</strong> 1.0</p><p><strong>Tarih:</strong> 25.06.2025</p><h2>1. Taraflar</h2><p>Bu sözleşme, “X Projesi” ile uygulamayı kullanan gerçek kişi (“Kullanıcı”) arasında akdedilmiştir.</p><h2>2. Hizmetin Kapsamı</h2><p>X Projesi, kullanıcıya dijital bir hizmet sunar. Kullanıcı bu hizmeti kişisel amaçlarla ve yasalara uygun şekilde kullanacağını kabul eder.</p><h2>3. Kullanıcının Yükümlülükleri</h2><ul><li>Yanıltıcı, yasa dışı ya da üçüncü şahıs haklarını ihlal eden bilgiler paylaşamaz.</li><li>Hesabına ait bilgilerin güvenliğinden kullanıcı sorumludur.</li><li>Uygulama üzerinden yapılacak tüm işlemler kullanıcıya aittir.</li></ul><h2>4. Hizmetin Sınırları</h2><p>X Projesi, hizmeti her zaman, herhangi bir gerekçe göstermeksizin değiştirme, durdurma veya sonlandırma hakkını saklı tutar.</p><h2>5. Fesih</h2><p>Kullanıcı, uygulamayı silerek sözleşmeyi feshedebilir. X Projesi de kullanıcı hesabını ihlal halinde kapatma hakkını saklı tutar.</p><h2>6. Uyuşmazlıklar</h2><p>Taraflar arasında çıkabilecek uyuşmazlıklarda Türkiye Cumhuriyeti yasaları uygulanır.</p></section>")
                .type(AgreementType.USER_AGREEMENT)
                .build();

        Agreement privacyAgreementEn = Agreement.builder()
                .version(1)
                .cultureName("en-EN")
                .content("<section><h1>Privacy Policy</h1><p><strong>Version:</strong> 1.0</p><p><strong>Date:</strong> 25.06.2025</p><h2>1. Data Collection</h2><p>X Project collects personal information such as name, surname, and phone number from users solely for the purpose of providing services.</p><h2>2. Data Usage</h2><p>The collected data is used for the following purposes:</p><ul><li>Providing services</li><li>Account security</li><li>Feedback and support processes</li></ul><h2>3. Data Sharing</h2><p>User data is not shared with third parties except where legally required.</p><h2>4. Data Security</h2><p>Personal data is protected with technical and administrative measures. However, 100% security cannot be guaranteed in online environments.</p><h2>5. Data Retention</h2><p>Personal data is retained for as long as required by relevant legislation, after which it is deleted or anonymized.</p><h2>6. Your Rights</h2><p>As a user, you can exercise your rights under applicable data protection laws by contacting us at <a href=\"mailto:kvkk@xprojesi.com\">kvkk@xprojesi.com</a></p></section>")
                .type(AgreementType.PRIVACY_AGREEMENT)
                .build();

        Agreement userAgreementEn = Agreement.builder()
                .version(1)
                .cultureName("en-EN")
                .content("<section><h1>User Agreement</h1><p><strong>Version:</strong> 1.0</p><p><strong>Date:</strong> 25.06.2025</p><h2>1. Parties</h2><p>This agreement is made between the “X Project” and the individual who uses the application (“User”).</p><h2>2. Scope of Service</h2><p>X Project provides a digital service to the user. The user agrees to use this service for personal purposes and in accordance with the law.</p><h2>3. User Responsibilities</h2><ul><li>The user may not share misleading, illegal, or rights-infringing content.</li><li>The user is responsible for the security of their account information.</li><li>All actions taken through the application are the responsibility of the user.</li></ul><h2>4. Service Limitations</h2><p>X Project reserves the right to change, suspend, or terminate the service at any time without providing justification.</p><h2>5. Termination</h2><p>The user may terminate this agreement by deleting the application. X Project reserves the right to terminate the user’s account in case of a violation.</p><h2>6. Disputes</h2><p>In case of disputes, the laws of the Republic of Turkey shall apply.</p></section>")
                .type(AgreementType.USER_AGREEMENT)
                .build();

        List<Agreement> agreements = agreementRepository.findAll();
        if (agreements.isEmpty()) agreementRepository.saveAll(List.of(privacyAgreementTr, userAgreementTr, privacyAgreementEn, userAgreementEn));
    }

    private void addRoles() {
        if (!roleRepository.existsByName(RoleType.ROLE_ADMIN)) roleRepository.save(Role.builder().name(RoleType.ROLE_ADMIN).build());
        if (!roleRepository.existsByName(RoleType.ROLE_USER)) roleRepository.save(Role.builder().name(RoleType.ROLE_USER).build());
    }

    private void addUsers() {
        userRepository.findByUsername("admin").ifPresentOrElse(
                user -> {},
                () -> {
                    Role role = roleRepository.findByName(RoleType.ROLE_ADMIN)
                            .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.ROLE_NOT_FOUND)));

                    List<AgreementDto> agreementDtos = agreementService.currentAgreements();
                    AgreementDto privacyAgreement = agreementDtos.stream().filter(a -> a.getType() == AgreementType.PRIVACY_AGREEMENT).findFirst()
                            .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.AGREEMENT_NOT_FOUND)));
                    AgreementDto userAgreement = agreementDtos.stream().filter(a -> a.getType() == AgreementType.USER_AGREEMENT).findFirst()
                            .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.AGREEMENT_NOT_FOUND)));

                    User user = User.builder()
                            .name("Admin")
                            .surname("User")
                            .phoneCode("+90")
                            .phoneNumber("5999999999")
                            .username("admin")
                            .password(passwordEncoder.encode("123123"))
                            .roles(Set.of(role))
                            .privacyAgreement(modelMapper.map(privacyAgreement, Agreement.class))
                            .userAgreement(modelMapper.map(userAgreement, Agreement.class))
                            .build();

                    userRepository.save(user);
                }
        );
    }
}
