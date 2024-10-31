import SwiftUI

extension Color {
    init(hex: String) {
        var cleanHexCode = hex.trimmingCharacters(in: .whitespacesAndNewlines)
        cleanHexCode = cleanHexCode.replacingOccurrences(of: "#", with: "")
        print(cleanHexCode)
        var rgb: UInt64 = 0
        
        Scanner(string: cleanHexCode).scanHexInt64(&rgb)
        
        let redValue = Double((rgb >> 16) & 0xFF) / 255.0
        let greenValue = Double((rgb >> 8) & 0xFF) / 255.0
        let blueValue = Double(rgb & 0xFF) / 255.0
        self.init(red: redValue, green: greenValue, blue: blueValue)
    }
}

struct ContentView: View {
    var body: some View {
        VStack {
            // Header
            HStack {
                
                Text("Chrysalis")
//                    .font(.title)
//                    .bold()
                    .font(.custom("Nunito-ExtraBold", size: 30))
//                    .italic()
                    .foregroundColor(Color.white)
                
                Spacer()
                
                Image(systemName: "calendar") // Profile icon
                    .font(.system(size: 30))
                    .foregroundColor(Color.white)

                
                Image(systemName: "person.circle") // Leaderboard icon
                    .font(.system(size: 30))
                    .foregroundColor(Color.white)

            }
            .padding(.horizontal, 22)
            .padding(.vertical, 10)
            .background(Color(UIColor.systemTeal).opacity(0.8)) // Soft teal for header
            
            // Scrollable Body
            ScrollView {
                VStack(spacing: 15) {
                    // First prompt (daily prompt)
                    RoundedRectangle(cornerRadius: 15)
                        .fill(Color.white)
                        .frame(height: 150)
                        .overlay(
                            Group {
                                @State var user_answer = ""
                                
                                RoundedRectangle(cornerRadius: 15)
                                    .stroke(Color(hex: "#009688").opacity(0.7), lineWidth: 4) // Pale blue border
                                
                                VStack(alignment: .center, spacing: 10) {
                                    Text("Today's Question")
                                        .fontWeight(.bold)
                                        .font(.custom("Nunito-SemiBold", size: 22))
                                        .font(.system(size: 20))
                                        .foregroundColor(Color(UIColor.systemTeal).opacity(1)) // Calming text color
                                    
                                    TextField("Write about one thing you're grateful for today.", text: $user_answer)
                                        .font(.custom("Nunito-SemiBold", size: 20))
                                        .lineLimit(nil)
                                        .multilineTextAlignment(.center)
                                    
                                    Spacer()
                                }
                                .padding(.vertical, 20)
                                .padding(.horizontal, 20)
                            }
                        )
                    
                    // Other prompts
                    ForEach(1..<10) { promptly_n in
                        RoundedRectangle(cornerRadius: 15)
                            .fill(Color.white)
                            .stroke(Color(hex: "#009688").opacity(0.3), lineWidth: 4) // Pale blue border
                            .frame(height: 150)
                            .overlay(
                                VStack {
                                    Text("User \(promptly_n)")
                                        .foregroundColor(Color(hex: "#009688").opacity(0.7)) // Calm purple text
//                                        .font(.headline)
                                        .font(.custom("Nunito-Semibold", size: 20))
                                }
                            )
                    }
                }
                .padding()
            }
            .padding(.bottom, 0)
            .background(Color(UIColor.systemTeal).opacity(0.05)) // Soft teal for footer
            .overlay(
                Rectangle()
                    .frame(height: 1) // Set the border thickness
                    .foregroundColor(Color(UIColor.systemTeal).opacity(0.5)),
                alignment: .bottom // Position the border at the top
            )

            
            // Footer
            HStack {
                Button(action: {
                    print("Map tapped")
                }) {
                    VStack {
                        Image(systemName: "mappin.and.ellipse")
                            .font(.system(size: 22))
                            .foregroundColor(Color(UIColor.systemTeal).opacity(1)) // Soft blue icon
                    }
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                
                Spacer()
                
                Button(action: {
                    print("Camera tapped")
                }) {
                    VStack {
                        Image(systemName: "pencil.and.scribble")
                            .font(.system(size: 30))
                            .foregroundColor(Color(UIColor.systemTeal).opacity(1))
                    }
                }
                .frame(maxWidth: .infinity, alignment: .center)
                
                Spacer()
                
                Button(action: {
                    print("Profile tapped")
                }) {
                    VStack {
                        Image(systemName: "person.3")
                            .font(.system(size: 22))
                            .foregroundColor(Color(UIColor.systemTeal).opacity(1))
                    }
                }
                .frame(maxWidth: .infinity, alignment: .trailing)
            }
            
            .padding(.horizontal, 55)
            
            .padding(.top, 10)
            
            .padding(.bottom, 40)
            
                //            .background(Color(UIColor.systemTeal).opacity(0.2)) // Soft teal for footer
        }
        .edgesIgnoringSafeArea(.bottom)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
