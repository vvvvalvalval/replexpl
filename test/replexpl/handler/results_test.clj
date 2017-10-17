(ns replexpl.handler.results-test
  (:require [midje.sweet :refer :all]
            [replexpl.handler.results :refer :all]))

(fact
  (extract-page {:id "8888384363", :name "ZZ Top", :fan_count 4965186})
  => {:page-id "8888384363", :page-name "ZZ Top", :n-likes 4965186}
  )

(fact
  (extract-last-post
    {:data [{:message "T-minus 15 days until our SOLD OUT show in #SaltLakeCity on 8/31! SHARE if you have your tickets!",
             :created_time "2017-08-16T19:38:50+0000",
             :id "8888384363_10154215302514364"}
            {:message "It's not too late to grab your tickets for our show at Hampton Beach Casino Ballroom in Hampton Beach, NH on 10/28! hyperurl.co/HamptonBeachNH",
             :created_time "2017-07-31T18:12:33+0000",
             :id "8888384363_10154175840844364"}
            {:message "Now, these are some good looking cartoons. Credit: Dan Reynolds",
             :created_time "2017-07-28T17:07:22+0000",
             :id "8888384363_10154168126504364"}
            {:message "You can never have too many throwbacks. #TBT",
             :created_time "2017-07-27T16:06:04+0000",
             :id "8888384363_10154165387809364"}
            {:message "New Buffalo, MI! Our show at Four Winds Casino Resort is sneakin' up on 9/22! Tickets here: hyperurl.co/NewBuffaloMI",
             :created_time "2017-07-25T18:53:34+0000",
             :id "8888384363_10154159570024364"}
            {:message "Gear up with #ZZTop official merchandise from our website!",
             :created_time "2017-07-21T17:48:02+0000",
             :id "8888384363_10154148254689364"}
            {:message "Your keys are looking a little boring. Time for an upgrade! https://goo.gl/cVPidT #ZZTopMerch",
             :created_time "2017-07-19T23:00:00+0000",
             :id "8888384363_10154142801069364"}
            {:message "Headed south for the 2017 South Carolina State Fair on 10/18! Who will we see there?",
             :created_time "2017-07-17T19:28:03+0000",
             :id "8888384363_10154137531289364"}
            {:message "Let's go for a ride. #NationalMotorcycleDay",
             :created_time "2017-07-14T16:00:00+0000",
             :id "8888384363_10154127284349364"}
            {:message "Need that perfect gift, but cant decide what they’d like? We’ve got the perfect thing. The official ZZ Top store has E-Gift Cards, so your fan can pick a gift they’ll truly love. https://goo.gl/g8McgW #ZZTopMerch",
             :created_time "2017-07-13T23:00:00+0000",
             :id "8888384363_10154125124699364"}
            {:message "Get ready, #NewJersey! We're tearin' up Count Basie Theatre in Red Bank on 10/24! Tickets available here: hyperurl.co/RedBankNJ",
             :created_time "2017-07-11T19:52:13+0000",
             :id "8888384363_10154119286229364"}
            {:message "SHARE if you've seen #SharpDressedMan live!",
             :created_time "2017-07-10T16:48:51+0000",
             :id "8888384363_10154116329614364"}
            {:message "Our show at Mystic Lake Casino in Prior Lake, MN is getting closer! Who has their tickets? hyperurl.co/PriorLakeMN",
             :created_time "2017-07-07T18:59:57+0000",
             :id "8888384363_10154108708329364"}
            {:message "Nothin' like a good, old-fashioned black & white portrait. #TBT",
             :created_time "2017-07-06T20:02:03+0000",
             :id "8888384363_10154106135599364"}
            {:message "Wishing everyone a happy, safe, and patriotic #4thOfJuly! 🇺🇸",
             :created_time "2017-07-04T17:00:00+0000",
             :id "8888384363_10154088904524364"}
            {:message "Happy #CanadaDay! We will be celebrating on 8/24 at PNE Amphitheatre in #Vancouver! 🇨🇦",
             :created_time "2017-07-01T20:00:00+0000",
             :id "8888384363_10154088891599364"}
            {:message "We're celebrating KSHE 95 - Real Rock Radio's 50th birthday with our friend Sammy Hagar (The Red Rocker) in Maryland Heights, MO on 9/30! Join the party: hyperurl.co/MarylandHeightsMO",
             :created_time "2017-06-30T17:13:29+0000",
             :id "8888384363_10154087859089364"}
            {:message "#Legs AND #Tush. #TBT", :created_time "2017-06-29T17:05:12+0000", :id "8888384363_10154084724434364"}
            {:message "Our very own Billy F Gibbons ripped a couple of chords with Noisey. Check it out:",
             :created_time "2017-06-26T17:00:00+0000",
             :id "8888384363_10154074986514364"}
            {:message "Keep the good times rollin'. #FBF",
             :created_time "2017-06-23T19:00:00+0000",
             :id "8888384363_10154067885334364"}
            {:message "#Norfolk, NE, it's almost time! We're headin' to Divots Events Center on 9/3. Tickets: hyperurl.co/NorfolkNE",
             :created_time "2017-06-22T17:38:19+0000",
             :id "8888384363_10154065327849364"}
            {:story "ZZ Top shared WAAF's post.",
             :created_time "2017-06-20T16:46:29+0000",
             :id "8888384363_10154059813794364"}
            {:message "Give me all your Lovin’ https://goo.gl/jfak7n",
             :created_time "2017-06-16T23:00:00+0000",
             :id "8888384363_10154050171229364"}
            {:message "It doesn't get any more #American than this. #FlagDay",
             :created_time "2017-06-14T17:01:43+0000",
             :id "8888384363_10154044955529364"}
            {:message "Fall will be here sooner than you know it, #Alpharetta, GA! Save the date: hyperurl.co/AlpharettaGA",
             :created_time "2017-06-13T18:55:49+0000",
             :id "8888384363_10154042580454364"}],
     :paging {:previous "https://graph.facebook.com/v2.8/8888384363/posts?since=1502912330&access_token=EAACEdEose0cBAEyo24ZBXJqmdYOS3JqOHCT1Gw1JZCD1LwXQM08nrUC9rRnZCnKiB9vkIfBvNcf1XOM3DcDXyCj1QpSfBv8AxFdKWDM32wsc5dZA2BZC6u1Ni9efWkThU51gpqH9JppRb1gDYItUezuYtAmcuJ3E0VPBrmDDK9H9Cp8SmaFO4jkZB5ZAYZACwtJuYKeWXQbCvAZDZD&limit=25&__paging_token=enc_AdDZBYHOLErswEWrqDr2wzxiiPjoXeigPW4LA276Yw02wwxlsOZBslOXeua8tOF4OEZBZCGtmi7XB2fK4GB2Blu3hRG6&__previous=1",
              :next "https://graph.facebook.com/v2.8/8888384363/posts?access_token=EAACEdEose0cBAEyo24ZBXJqmdYOS3JqOHCT1Gw1JZCD1LwXQM08nrUC9rRnZCnKiB9vkIfBvNcf1XOM3DcDXyCj1QpSfBv8AxFdKWDM32wsc5dZA2BZC6u1Ni9efWkThU51gpqH9JppRb1gDYItUezuYtAmcuJ3E0VPBrmDDK9H9Cp8SmaFO4jkZB5ZAYZACwtJuYKeWXQbCvAZDZD&limit=25&until=1497380149&__paging_token=enc_AdA5zcUGRiK0HXo7oW7qUsuYJgZAYMRj0ZBAE5gt31XmUXUZB1RCTL7eZAyJCzqW520YHQJMjgWMFrVlyzYtGZBBMxZBzZB"}}
    )
  => "T-minus 15 days until our SOLD OUT show in #SaltLakeCity on 8/31! SHARE if you have your tickets!"
  )
