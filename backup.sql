PGDMP     4                
    x            srmplayground_prod    12.4    12.4 4    A           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            B           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            C           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            D           1262    16393    srmplayground_prod    DATABASE     �   CREATE DATABASE srmplayground_prod WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
 "   DROP DATABASE srmplayground_prod;
                postgres    false            �            1259    16396    customer    TABLE     �   CREATE TABLE public.customer (
    id bigint NOT NULL,
    name character varying(255),
    "position" integer NOT NULL,
    driver_id bigint
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    16394    customer_id_seq    SEQUENCE     x   CREATE SEQUENCE public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.customer_id_seq;
       public          postgres    false    203            E           0    0    customer_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;
          public          postgres    false    202            �            1259    16404    driver    TABLE     X   CREATE TABLE public.driver (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.driver;
       public         heap    postgres    false            �            1259    16402    driver_id_seq    SEQUENCE     v   CREATE SEQUENCE public.driver_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.driver_id_seq;
       public          postgres    false    205            F           0    0    driver_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.driver_id_seq OWNED BY public.driver.id;
          public          postgres    false    204            �            1259    16412    orders    TABLE     �   CREATE TABLE public.orders (
    id bigint NOT NULL,
    created_date timestamp without time zone,
    status boolean,
    customer_id bigint,
    driver_id bigint
);
    DROP TABLE public.orders;
       public         heap    postgres    false            �            1259    16410    orders_id_seq    SEQUENCE     v   CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    207            G           0    0    orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
          public          postgres    false    206            �            1259    16420    product_list    TABLE     �   CREATE TABLE public.product_list (
    id bigint NOT NULL,
    count double precision NOT NULL,
    type character varying(255),
    order_id bigint,
    product_id bigint
);
     DROP TABLE public.product_list;
       public         heap    postgres    false            �            1259    16418    product_list_id_seq    SEQUENCE     |   CREATE SEQUENCE public.product_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.product_list_id_seq;
       public          postgres    false    209            H           0    0    product_list_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.product_list_id_seq OWNED BY public.product_list.id;
          public          postgres    false    208            �            1259    16428    products    TABLE     q   CREATE TABLE public.products (
    id bigint NOT NULL,
    name character varying(255),
    id_product bigint
);
    DROP TABLE public.products;
       public         heap    postgres    false            �            1259    16426    products_id_seq    SEQUENCE     x   CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    211            I           0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
          public          postgres    false    210            �            1259    16436    type_product    TABLE     ^   CREATE TABLE public.type_product (
    id bigint NOT NULL,
    name character varying(255)
);
     DROP TABLE public.type_product;
       public         heap    postgres    false            �            1259    16434    type_product_id_seq    SEQUENCE     |   CREATE SEQUENCE public.type_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.type_product_id_seq;
       public          postgres    false    213            J           0    0    type_product_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.type_product_id_seq OWNED BY public.type_product.id;
          public          postgres    false    212            �
           2604    16399    customer id    DEFAULT     j   ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);
 :   ALTER TABLE public.customer ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203            �
           2604    16407 	   driver id    DEFAULT     f   ALTER TABLE ONLY public.driver ALTER COLUMN id SET DEFAULT nextval('public.driver_id_seq'::regclass);
 8   ALTER TABLE public.driver ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205            �
           2604    16415 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    207    207            �
           2604    16423    product_list id    DEFAULT     r   ALTER TABLE ONLY public.product_list ALTER COLUMN id SET DEFAULT nextval('public.product_list_id_seq'::regclass);
 >   ALTER TABLE public.product_list ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    209    209            �
           2604    16431    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    211    211            �
           2604    16439    type_product id    DEFAULT     r   ALTER TABLE ONLY public.type_product ALTER COLUMN id SET DEFAULT nextval('public.type_product_id_seq'::regclass);
 >   ALTER TABLE public.type_product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    213    213            4          0    16396    customer 
   TABLE DATA           C   COPY public.customer (id, name, "position", driver_id) FROM stdin;
    public          postgres    false    203   �8       6          0    16404    driver 
   TABLE DATA           *   COPY public.driver (id, name) FROM stdin;
    public          postgres    false    205   �@       8          0    16412    orders 
   TABLE DATA           R   COPY public.orders (id, created_date, status, customer_id, driver_id) FROM stdin;
    public          postgres    false    207   �@       :          0    16420    product_list 
   TABLE DATA           M   COPY public.product_list (id, count, type, order_id, product_id) FROM stdin;
    public          postgres    false    209   �A       <          0    16428    products 
   TABLE DATA           8   COPY public.products (id, name, id_product) FROM stdin;
    public          postgres    false    211   (D       >          0    16436    type_product 
   TABLE DATA           0   COPY public.type_product (id, name) FROM stdin;
    public          postgres    false    213   �R       K           0    0    customer_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.customer_id_seq', 167, true);
          public          postgres    false    202            L           0    0    driver_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.driver_id_seq', 7, true);
          public          postgres    false    204            M           0    0    orders_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.orders_id_seq', 86, true);
          public          postgres    false    206            N           0    0    product_list_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.product_list_id_seq', 596, true);
          public          postgres    false    208            O           0    0    products_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.products_id_seq', 374, true);
          public          postgres    false    210            P           0    0    type_product_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.type_product_id_seq', 18, true);
          public          postgres    false    212            �
           2606    16401    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    203            �
           2606    16409    driver driver_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.driver
    ADD CONSTRAINT driver_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.driver DROP CONSTRAINT driver_pkey;
       public            postgres    false    205            �
           2606    16417    orders orders_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    207            �
           2606    16425    product_list product_list_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.product_list
    ADD CONSTRAINT product_list_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.product_list DROP CONSTRAINT product_list_pkey;
       public            postgres    false    209            �
           2606    16433    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    211            �
           2606    16441    type_product type_product_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.type_product
    ADD CONSTRAINT type_product_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.type_product DROP CONSTRAINT type_product_pkey;
       public            postgres    false    213            �
           2606    16447 "   orders fk624gtjin3po807j3vix093tlf    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk624gtjin3po807j3vix093tlf FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk624gtjin3po807j3vix093tlf;
       public          postgres    false    207    203    2724            �
           2606    16467 $   products fk96s3i1t205cb3r558yhqek4et    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk96s3i1t205cb3r558yhqek4et FOREIGN KEY (id_product) REFERENCES public.type_product(id);
 N   ALTER TABLE ONLY public.products DROP CONSTRAINT fk96s3i1t205cb3r558yhqek4et;
       public          postgres    false    211    2734    213            �
           2606    16462 (   product_list fkjqo2nol7nc0358tleccklght6    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_list
    ADD CONSTRAINT fkjqo2nol7nc0358tleccklght6 FOREIGN KEY (product_id) REFERENCES public.products(id);
 R   ALTER TABLE ONLY public.product_list DROP CONSTRAINT fkjqo2nol7nc0358tleccklght6;
       public          postgres    false    209    2732    211            �
           2606    16452 "   orders fklfsgolihtmfujlg0egc76sh8w    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fklfsgolihtmfujlg0egc76sh8w FOREIGN KEY (driver_id) REFERENCES public.driver(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fklfsgolihtmfujlg0egc76sh8w;
       public          postgres    false    205    207    2726            �
           2606    16457 (   product_list fkrqr7kcus5y993wjdi5vc4w8gs    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_list
    ADD CONSTRAINT fkrqr7kcus5y993wjdi5vc4w8gs FOREIGN KEY (order_id) REFERENCES public.orders(id);
 R   ALTER TABLE ONLY public.product_list DROP CONSTRAINT fkrqr7kcus5y993wjdi5vc4w8gs;
       public          postgres    false    207    2728    209            �
           2606    16442 $   customer fkt71jly5mggpt598nklsjsfd9q    FK CONSTRAINT     �   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fkt71jly5mggpt598nklsjsfd9q FOREIGN KEY (driver_id) REFERENCES public.driver(id);
 N   ALTER TABLE ONLY public.customer DROP CONSTRAINT fkt71jly5mggpt598nklsjsfd9q;
       public          postgres    false    2726    205    203            4   �  x��YIrG<^1�ݳ�ŏ(S�B
���aY��W,b��̏�Y=K׀��֮����ʪn������_v�ܖ��ܔ�r_��QT���'��Pn�,���:�j
���Um�6#��~���\���\���[V��9����A��7��� �b�����+�M���߾�!f���?��o����о��x�u���8����6�rY6�A��Z.�li#�-�l����ۖ�5���u��K[�ۯ��cu�Y��M��D�6$�&��< �CpM��to���n�4&�F�����x�����s��4g��c0#&}i�j"Ȭ���0U�����0`R�$`�*0L�ɇl%��yNL�Q�;���޶�a
-R���B��BX�|�����]`�Dk4�WL$6P�3�y�v��恕"RidI^� �uv,Si�A|���=f�&�v�P8)D&ʪD�:��ٰ�r��B5`���<�<���%�n��a�I�%�Ѹ����Ss� "��l^#�#�?4F�^��/���Y�&a$�ئЃ�/�mWQ��,,ד6���y'8E\L����`�U�.L _XV���Z�
H�b�O�5�ˎ�6~�%K�[����5���g��c���ǭ�p[}��Մ9OGq��H���3ׂ��0|1�q�ډT�'}���"x��Z	�az*�;�@�@ǚ���lL�t�N�Q0���@]�J]��`2V��~s�B#1T(U�VN�pj���Ķ��:P��咲�5�Q���ɓ�#�M|�\���c�c��%��� ܺ�"�$;w K���fE�D�t��%��Y� H���6:��0�Cv��u�VdN�1���Y�JS{�?�g-�3�'��F}�qbʒ4A4J��n�h�P*
���6�~!�H,�`1M���H�,�����k��/H����!� �PЄ�`Vc5�y�A�(k���'��[l��j5:��y�kE��r�ޚqk�b����>��e(o��S�`y2:>@ĽQ&*R������w�-�,��˖�Nɇ�t�)H5��<�����5�B1�ytZ���t>v���_J��Y{a�sU$���2��Z����E�ɓ~Irl{t}Tn-��t����Mωh��4o�>NUbZ�&0�y�hf�B��N�����6fwZS����oQ#����|�����ږS*�Z�P�ZlݲU�M�
@��ҋdB4Ģ��AO&�x�Kz�:�}���Jr �4�_�щ\a�΃� ���*���}.wȏ�%���5�k�>���`
�R"t'�vY��҂Y5��Ҭ�^�<s�f�X�mY�+��J�ݸ������;%�n(`s�ZUr�
6��b�u_s=b��	^@/t+��eO�®�D��c>�C�~�u�<#�a�P�#��$os�&�%N��8g:J$�R��W��#i5�;��J��q1�XB��0ڞo�z���KV��s��U�o��I�N,ɷɀ�PR�n�^	O�N,9���قv��խ����}h��K���7�^|����� ���%g�_������y��?72�=�{�n�~����r&;]l�:s�ވ21˴ �{ݏ���K�<��>��?ұ�W�Dk���x<<��f$��j,1D���v��[K�uN��׹y3���a�]�*��a�� �,�9�7~��
$y>H�������Es�VN��+�?5B2@�%hZ�C�6��$"����l���A��{v���T������
C��yAo��w+t����F{�Q&l.��%��s�D�/���$���+;��A+/�P ��,򢰇Sc.շ��p�n�����B��?-�.��)�G�3>L���Q����	�pՎ3��V8�^�����B�0l�)�q �'!��=/�d>�߿�&}�5��I��z�2I���0d�mD����s�SF�f^�$�i_�aь�S��O���֒��      6   e   x�3估�bÅ}v\l��K���U��˘���[/l��(��b��{��L�	q�r^�a7P��b�G�b;�r.s��.�	\�qa�}\1z\\\ ��UR      8   �   x�e��q�@Eѵ����&�8������w��bd�}Pȓ%� l_�ߋ8�u{Lc���1�e�4+yS;d� Bi��*C�X�I?����i<��F���$+рT�Ԫ����Y%mv�&�x�س�9ǧى�=˨̬�xl��L���*�
���<	ge�$���e�+ff����mjW��i��kW�̆I���+fgӤ[�f�2��Y��c���1;[�F�F���I
�{����}�bt��      :     x�USK�![3�I��1�%�I�lr�쒪�+��(�q3�V��%�O[���2�,{X�d(�q"A�^?��D�. �
d}���Ԁzkq��M��q�"�����Xx��������;ؤ��O�z2��2��N9���`,T�-\(�e���"�.B����P�'�va�����G`Ƭq{y����e&la�>V�g�Hǽ�/�"�@��	�*H��7c�e�8�1d���P��[���hQ@�ֹD{���Z&��r�$]#��9�Ⰴ�6f[��(���4��^!�C��^�A�!���b��4�>EE�sĚ�B֯��/F�Q���fa��N�lݑ66j���z��2����������)`�%���.����XQ"�:���8���|�DA���v���~V><��]zm���)��I�~@��~���� XI@Cg=�3�h?قU����ij��7��\���8��9�~P�d�5%�ɉ�3WT[�t����)����%�Ł��v��Dψ�vK�~������{<�#;\      <   V  x��Zے�}&��/y�����_�1�]I+�dˊ\N�q�q*�S�%��%��/��Q��f���rY�=@��@'������4O�n־n��\~����&Y<i~h62�m��U4��i�T���f�^�8kNͱY��3��h_�r�JA�ATsh6�~-��f1�^U��F�|\Fw�v�^�����Qr쓛��f�l�Y	�+Y�A��,�C�����1�y��sჳI�@��Еl�^���(��'�\N��d���A���b���R���U��?.� ��YF�����L��m�F�$P �Ȏ[��Hw�ΉsN�ז]�g|�޵��I���x:�u<�r,�	=I�Z>��AŤ�W���]���:Z�����O��Y�w.��N��tN��V��7ޙ��Թ��!N"�}�!����L��w��	��f��dW�I��
�W",90���Tu�+��$����$e��(�ኊ��)�=���R���&�;`E/�!�`�G.Mu���$@]^�o�#��Y�l�����>��}�%��,g<�N��,�}�E|-�吇���@�^��0���M�,���Ƒa "X�oD�?e�l�}���F�G�']*�	���Z$t�����HK$NX+f �� !��b��;�r�t�Y[�	���6�dM� 58�{���y�9�i\9��r���G���M@"���B�b�K��W]�dO8��?{��3��s��D�1/�h��$��}߇��I�="��y�2��,���]�p��[Hb�(Z>F�x?ɷ�@�蝊�]�H�3��>��� y�c����¿�������Q:�@��Un�읳��M�t	"W�#�y����u�"-B"�!�y^Gq�{Q�x�)�dHZ��Z�dQ�T:zZrGMB��Q ��ド����t�6/PYL�)������
�~��*��1*7��!�'�Uq��d��r�i�X�e�� D8?��C8�gT�p��I��k���7��!j����:͊2]�\�Qok'�(0�}�@Ļax�����7?�A,}��iB�|��>Vm]�����Y_A��%�&`���!������ xG���#�RY 5����?;��|�у4*�N�|�5C@qYg�C�(Gߩ���@ȵ`� ���IX:-E���x�m��{�0�Q~{TB�4��y��4��׉HBr/a�k��[h����}֑M���O2�C&�&1O�ڑ;
�����=�{�  U�KATQ���В�&�J�@�JD�h��t���?�r@�;o8�r� a!	��!�٨l��=��������}=]��(�jXPR]�ѣ�%���v��Y2[DM~����u[L�i�8����+!��ɪ�4q���zl{�H
�}�^�j�:�f�>�w-��B.]r���"
�?~�����qn��BR�hHQ� )���e@f0���u-�X-��dBd�!=�F: ���aN{ 5���2���P�U�Y�e���ɩ��=ߠ#�rh+r^�IlI���B5E�
���~Q�H�q�CQQ���h�C�A���~u����PM 5�����J�|�u}c�� 7k:-��E�M<��h�c-��a�4H2+�O �,��VR=$u��>�C���Ս���gG�.)��J�L������[�'T�0���V��bX�:"7�K\iS�"��:n�O��dSm�Z.n[�P4�huL*�$��ñ?�F:��Ùf[L+�5��J*�,j)�,H��/Lǐ��<&�BI+fh1V��t%�1��
������k�3����3��K�+�C����,\�A�i�d�����32��Ԧ��^��o(2|�pn�A_�΁�Ss���oZ@�� �b&N=8u� F0�?[��
_�����O`���BhpL�WX���S�Ks���u�f���_��}���vP"�_d�K�*��;U��u�z`8 8a��Cp�;����Ǒ;�����f�n�~e?�4�(�q"2_��x׆���­4t)ќϗ�߻�9���Z�W�og+e�&��\��}A��ؓ�؏;c"���y�f�%�㹧��c@E4���1ll�	�G���=�~�����j���!�;pp�/�ߒN�Z�"�oI�˸1~�Uv�f`p6VgKʩ�}���yg
ؿE����.�-�r�(P��]ZT�{]X�_��� �R����� 0��B��Ϡ����C%�@؇߱�}�j�����E�^+<�����
�33'�~VG��e�ɲn�]Xr�'O�8�a���O��SW��|Be�虥��ě\�_<�Q�v(�&Q$�~��H��Gh_j	"LR�%	rIpr:"���!B��s�UH�©t��Тm������H���\�Q>=W��bՓZ�!�H�9��<�8UyO\���c4�#;��}�� �8!��q�puJ�}��[�P�a����O����x0�4��O�]s�+R�hI{oמ�G�ԃm��w+a3���-_�X�s8�k����^��$^fic�7�j<q"�
��	�潎֦��~)�'@	�膩�C8�6H�UEx�����(�*&[|�)�`�ܽ �&�y���A�?2w{P,x�[�2�4C!�E�ڎ�7ac���i���T�X���F�"N�\�H����N!�����W �z��q��0H<�	B��$#.o�Q�ap�z�|31\�.Kg��Z�E��Ӳ�4��������X��ua@��V=>��v`�<:�7�ݨ�VkZkڔr��^a�4ɢ!,
��>��z�hz`uAd�#�(� ���h���!�(�1�������%�δ�*.��.j��˥�L�o��
@m��� �Y@����<�I���k�wO!��.)A���`3��>g�J� @�ղ.��<3+ƸjĬ��̪!jj/<�K��"r�L�BFb}����z�D�$��_�8;U���׵�Kϐ뛋���\����@_ �ה�+Z�#?�>؃�|�-��g�U?Q�NcXh \ۇ�--��l��ؾ�)q/��O1j{s���BY6����� 9���y ��/�ġ��8�ʡ�R���j��X����D�9kE��8�E����qd�;��Jr�9G�h3+s�M����b�|G:B}��y�Km���!��&�����Vg�{$�̲]t��0�u�utf�s�F�L[̝�����Z����>!D\u�i 	߱�־�͂\��k�e����&�����ݖ�w�p��;�����
�]ѽ,ٛ�#p�zgf�Z.:E����ﺞ�A��ϼp��L2y���O_t�.:{ы���u��C(\�^z��KQB�C�\G�~�v}�C����wɱ�\�-q8{a�オ������� lgn���w
��C���OeB/��,��:@87�퉴�Yȗ*�;}l���L�B�S�9�
���N��|́��-�����t�o�W�7�l�H-���%Pj@�%P�ﺓ�<(��Ž�y��ܾO�K���*/�2����'m���y\x��ך�cû�'�tKАEQ�������ק������j��̣9)-���Ҋ-�:b�7ڧ{gM�(B�Q�}pQd=��:��N��\]�\�R=-���툥]��G��K�Ǎ���zԮ�$���z���ğ�8�N���g       >   �   x�UPIn�@<O���f���c)D�!.Q	xA&�''�|��G�v,��z��k�ë����'�8�P��O��d{$ԺE3��/,D\�q0sxǅ�$s��V�@iA��O�o$:����neq�K��ݰY�6O���u�r8P(jARdUk%���	J���I��\�eb���V�Y���#�th�3ϛ������j�6Z�c���~f��ȉ�����1��1Ԏ�P,gi�5;֦v�z,"���z     