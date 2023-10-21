# ProjectEE

## Quy trình làm việc


- ### ***Lấy source trên git về nếu máy tính chưa có***
- **Cách 1**
    Vào folder nơi muốn chứa file và mở terminal 
    - **git clone url** => (url => là đường link từ trên github bấm nút màu xamh lá (<> Code) sẽ thấy))
    - **git pull**
    
- **Cách2**
    - Lên github bấm vào nút xanh lá download file .zip

    
- ### ***Làm việc khi đã có source trên máy***
  - Mở terminal và gõ **git clone url** => (url => là đường link từ trên github bấm nút màu xamh lá (<> Code) sẽ thấy))
  - **git checkout master** => (chuyển sang nhánh master)
  - **git pull** => (lấy toàn bộ code đã được các thành viên thay đổi)
  - **git checkout -b <branchName>** (tạo nhánh của mình nếu chưa có < ***có rồi thì bỏ -b*** >)
 - => *//tiếp tục làm việc trên branchName của mình*

- ### ***Đẩy source sau khi đã code xong mỗi phiên làm việc***
  ***Kiểm tra đang ở branch nào***
    - **git branch** => xem dấu * đang nằm ở branch nào (phải là branch của mình)
  ***Push source nếu đã đúng branch của mình***
    - **git add .**
    - **git status** => kiểm tra trạng thấi những thay đổi có sẵn sàng được push
    - **git commit -m "Message"** => *Message => ghi tóm tắt nội dung đã thay đổi trên source*
    - **git push origin branchName** hoặc **git push** => *branchName => là nhánh của mình*
- ### ***Kiểm tra và tạo *Pull Request* sau khi *Push* code***

   - Lên github kiểm tra đã được push lên chưa và tạo **Pull Request**
   - Phần nội dung nên ghi chi tiết nội dung mình đã thay đổi

## Lưu ý

- *Không* sử dụng branch ***Master*** để *push* code tránh conflict
- Sau khi tạo **Pull Request** không nhấn vào nút ***Merge pull request***

<-- *Nếu có sai sót hãy update lại readme* -->
