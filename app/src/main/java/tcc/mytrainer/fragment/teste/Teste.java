package tcc.mytrainer.fragment.teste;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.uol.pslibs.checkout_in_app.PSCheckout;
import br.com.uol.pslibs.checkout_in_app.transparent.listener.PSCheckoutListener;
import br.com.uol.pslibs.checkout_in_app.transparent.listener.PSInstallmentsListener;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.InstallmentVO;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.PSCheckoutResponse;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.PSInstallmentsResponse;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.PSTransparentDefaultRequest;
import br.com.uol.pslibs.checkout_in_app.wallet.util.PSCheckoutConfig;
import tcc.mytrainer.R;

public class Teste extends AppCompatActivity implements PSCheckoutListener {

    String SELLER_TOKEN = "8A019ACD197F417C8D7B3778DD0B37E1";
    String SELLER_EMAIL = "jhow.mjy@gmail.com";

    InstallmentVO installmentVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        //INIT CHECKOUT
        PSCheckoutConfig psCheckoutConfig = new PSCheckoutConfig();
        psCheckoutConfig.setSellerEmail(SELLER_EMAIL);
        psCheckoutConfig.setSellerToken(SELLER_TOKEN);
        //Informe o fragment container
        psCheckoutConfig.setContainer(R.id.pagamento_fragment);

        //Inicializa apenas os recursos de pagamento transparente e boleto
        PSCheckout.initTransparent(this, psCheckoutConfig);

        PSTransparentDefaultRequest psTransparentDefaultRequest = new PSTransparentDefaultRequest();
        psTransparentDefaultRequest
                //(String) - Numero do documento
                .setDocumentNumber("08512122986")
                //(String) - Nome do comprador
                .setName("Marlon Jhow Yeung")
                //(String) - Email do comprador
                .setEmail("marlon.jhow@live.com")
                //(String) - Codigo de area do telefone do comprador
                .setAreaCode("41")
                //(String) - Numero de telefone do comprador
                .setPhoneNumber("999544514")
                //(String) - Rua do comprador
                .setStreet("Roque Lazarotto")
                //(String) - Complemento do endereço do comprador
                .setAddressComplement("apto")
                //(String) - Numero da casa do comprador
                .setAddressNumber("376")
                //(String) - Bairro do comprador
                .setDistrict("Boa Vista")
                //(String) - Cidade do comprador
                .setCity("Curitiba")
                //(String) - Estado do comprador
                .setState("PR")
                //(String) - País do comprador
                .setCountry("Brazil")
                //(String) - CEP do comprador
                .setPostalCode("82560420")
                //(String) - Minimo R$ 1.00 - Valor total da transação
                .setTotalValue("1.00")
                //(String) - Valor unitário do produto
                .setAmount("1.00")
                //(String) - Maximo 100 caracteres - Descrição do pagamento
                .setDescriptionPayment("Treino")
                //(int) - Quantidade de produtos escolhidos
                .setQuantity(1)
                //(String) - Numero do cartão de credito
                .setCreditCard("5162205824077422")
                //(String) - Codigo de segurança do cartão de credito
                .setCvv("159")
                //(String) - Mes de expiração do cartão de credito
                .setExpMonth("02")
                //(String) - Ano de expiração do cartão de credito
                .setExpYear("19")
                //(String) - formato: dd/MM/yyyy - Data de nascimento do comprador
                .setBirthDate("11/11/1991");
                //(Installments) - Objeto que é retornado no serviço de parcelamento que corresponde a parcela escolhida
                //.setInstallments(installmentVO);
        PSCheckout.payTransparentDefault(psTransparentDefaultRequest, this, this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PSCheckout.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onSuccess(PSCheckoutResponse psCheckoutResponse) {
                System.out.println("");

    }

    @Override
    public void onFailure(PSCheckoutResponse psCheckoutResponse) {
                System.out.println("");

    }

    @Override
    public void onProcessing() {
                System.out.println("");

    }
}
