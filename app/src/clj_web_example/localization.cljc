(ns clj-web-example.localization)

(def dictionary {:title "Hello World!"
                 :messages "Messages"
                 :new-message "New message"
                 :sender "Sender"
                 :message "Message"
                 :submit "Submit"})

(defn tr
  [key]
  (get dictionary key))
