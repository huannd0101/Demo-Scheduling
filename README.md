# Scheduling Tasks with Spring boot
<hr>

1. Để tạo ra các tác vụ định thời cần đánh dấu các Bean bằng <b>@EnableScheduling</b>
2. Để các tác vụ không chạy chồng chéo lên nhau cần đánh dấu <b>@ConditionalOnExpression("true")</b>
3. Để tắt luôn các tác vụ định thời <b>@ConditionalOnExpression("false")</b>
<hr>
Thêm @EnableSheduling vào class Application
<pre>
    @SpringBootApplication
    @EnableScheduling
    public class DemoApplication {
       public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
        }
    }
</pre>

<hr>

Các method định nghĩa trong Demo.class
### Fixed Delay Task
<pre>
    @Component //có thể thay thế bằng @configuration
    @EnableScheduling  //Đánh dấu đấy là component chứa tác vụ định thời
    @ConditionalOnExpression("true")  // Nạp component này vào ApplicationContext. "false": không nạp, hoặc vô hiệu hoá class này
    public class Demo {
        @Scheduled(fixedDelay = 1000) //delay 1s
        public void exc() {
            //code
        }
    }
</pre>
=> Thời gian mỗi tác vụ chạy có thể thay đổi, nhưng khoảng thời gian trễ giữa các tác vụ luôn cố định.
### Fixed Rate Task
<pre>
    @Component 
    @EnableScheduling  
    @ConditionalOnExpression("true")  
    public class Demo {
        @Scheduled(fixedRate = 1000) 
        public void exc() {
            //code
            //Nếu code chạy quá fixedRate thì khoảng cách chạy là time của code
        }
    }
</pre>
=> Cứ cách đúng 1 khoảng thời gian (fixed rate) là chạy. <br>
Nếu tác vụ trước chạy quá lâu, thì tác vụ sau chờ thêm đến điểm là bội số nhỏ nhất của fixed rate rồi chạy.

### Async Task
- Async Task sử dụng nhiều thread để thực thi các tác vụ định thời mà không phải chờ đợi nhau (Fixed Rate và Fixed Delay chỉ sử dụng 1 thread).
<pre>
    @EnableAsync //Bật chế độ Async
    @EnableScheduling
    @ConditionalOnExpression("true")
    @Component
    public class Demo {
      @Async //Đánh dấu tác vụ này sẽ chạy bất đồng bộ
      @Scheduled(fixedRate = 1000)
      public void exc() {
        //code
      }
    }
</pre>
- Nếu code trong method exc() chạy quá fixedRate thì không cần lo, @Async sẽ đảm bảo mỗi lần chạy cách nhau đúng fixedRate.

### Cron Task
- Nếu cần định thời trong khoản thời gian dài hơn: ngày, tháng, giờ.... thì nên dùng cron task. Cron task được định thời theo biểu thức truyền vào tham số cron
<pre>
    @Scheduled(cron = "0 * * ? * *") //Thời gian cách nhau 1 phút
    @Scheduled(cron = "0 0 12 * * ?") //Method sẽ được thực hiện vào 12h mỗi ngày
</pre>

<pre>
    @EnableScheduling
    @ConditionalOnExpression("true")
    @Component
    public class Dem0 {
      @Async //Đánh dấu tác vụ này sẽ chạy bất đồng bộ
      @Scheduled(cron = "0 * * ? * *")
      public void exc() {
        //code
      }
    }
</pre>
- Cách lấy cron: <a href="https://www.freeformatter.com/cron-expression-generator-quartz.html">Click here</a>

### Initial Delay

<pre>
    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
</pre>
=> Lần đầu tiên sẽ chạy với tham số initialDelay và các lần còn lại chạy với tham số fixedDelay

### Các trường hợp:
- Cuối ngày dọn rác server
- Gửi mail cho khách hàng nếu ngày đó không làm việc vào 8h tối
- Ứng dụng IOT thời tiết... 
