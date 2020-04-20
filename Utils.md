## Utils

폴더 주소 창에

<code>%username%</code>

입력해서 폴더 이동

.aws 폴더 생성 후 .aws 폴더에 credential 파일 저장(업로드, 다운로드 시 권한 확인용 없으면 profile 에러 발생)

객체 주입받기
<pre>
<code>
@Autowired
S3Utils s3Utils;
</code>
</pre>

## 파일 업로드하기

<pre>
<code>
s3Utils.fileUpload("profile/test1.jpg", file);
or
s3Utils.multipartUpload("profile/test1.jpg", multipartFile); 
// 멀티파트 파일 저장 시 
</code>
</pre>
파라미터
1. 경로(폴더) + 파일 이름


        ex) "proflle/test1.jpg" => S3의 profile 폴더에 test1.jpg 파일 저장

        폴더가 존재하지 않는 경우 폴더를 생성해줌

2. 파일

        저장할 파일

## 파일 다운로드하기

<pre>
<code>
S3object s3object = 3Utils.fileDownload(String key);
</code>
</pre>
파라미터
1. 경로(폴더) + 파일 이름

리턴

1. S3Object

        리턴이 S3Object이기 때문에 S3ObjectInputStream와 FileOuputStream을 이용해서 파일 객체 생성 후 옮겨야함

        ※S3Contorller 클래스의 getS3Image 메서드 참조

## 이미지 링크 걸기

img src

<pre>
<code>
 src="${pageContext.request.contextPath}/s3/이미지가 있는 S3 내의 폴더 이름/저장한 파일 이름"
</code>
</pre>
